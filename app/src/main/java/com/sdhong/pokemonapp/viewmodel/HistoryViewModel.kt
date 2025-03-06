package com.sdhong.pokemonapp.viewmodel

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdhong.pokemonapp.common.Formatter
import com.sdhong.pokemonapp.local.dao.HistoryDao
import com.sdhong.pokemonapp.local.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyDao: HistoryDao
) : ViewModel() {

    val historyPokemons: StateFlow<List<Pokemon.History>> = historyDao.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _isDeleteMode = MutableStateFlow(false)
    val isDeleteMode = _isDeleteMode.asStateFlow()

    fun onPokemonClick(pokemon: Pokemon.History) {
        viewModelScope.launch {
            if (_isDeleteMode.value) {
                historyDao.upsert(pokemon.copy(isChecked = !pokemon.isChecked))
            } else {
                historyDao.upsert(
                    pokemon.copy(
                        lastViewed = Formatter.dateFormat.format(Calendar.getInstance().time)
                    )
                )
            }
        }
    }

    fun toggleDeleteMode() {
        viewModelScope.launch {
            _isDeleteMode.value = !_isDeleteMode.value

            if (!_isDeleteMode.value) {
                historyDao.deleteChecked()
            }
            historyDao.updateDeleteMode(_isDeleteMode.value)
        }
    }
}