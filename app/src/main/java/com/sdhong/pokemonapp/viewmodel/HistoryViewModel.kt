package com.sdhong.pokemonapp.viewmodel

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdhong.pokemonapp.common.Formatter
import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.repository.PokemonRepository
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
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    val historyPokemons: StateFlow<List<Pokemon.History>> = pokemonRepository.flowHistoryPokemons()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _isDeleteMode = MutableStateFlow(false)
    val isDeleteMode = _isDeleteMode.asStateFlow()

    fun onPokemonClick(pokemon: Pokemon.History) {
        viewModelScope.launch {
            if (_isDeleteMode.value) {
                pokemonRepository.upsert(pokemon.copy(isChecked = !pokemon.isChecked))
            } else {
                pokemonRepository.upsert(
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
                pokemonRepository.deleteChecked()
            }
            pokemonRepository.updateDeleteMode(_isDeleteMode.value)
        }
    }
}