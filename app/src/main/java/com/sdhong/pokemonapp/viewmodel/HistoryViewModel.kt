package com.sdhong.pokemonapp.viewmodel

import androidx.lifecycle.ViewModel
import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.local.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : ViewModel() {

    val historyPokemons: StateFlow<List<Pokemon.History>> = historyRepository.historyPokemons

    private val _isDeleteMode = MutableStateFlow(false)
    val isDeleteMode = _isDeleteMode.asStateFlow()

    fun onPokemonClick(
        position: Int,
        startDetailActivity: (pokemonId: Int) -> Unit
    ) {
        val pokemon = historyPokemons.value[position]

        if (_isDeleteMode.value) {
            historyRepository.updateCheckbox(pokemon)
        } else {
            startDetailActivity(getPokemonId(pokemon.detailUrl))

            historyRepository.removePokemonHistory(pokemon)
            historyRepository.addPokemonHistory(pokemon)
        }
    }

    private fun getPokemonId(url: String): Int {
        return url.split("/")[6].toInt()
    }

    fun toggleDeleteMode() {
        _isDeleteMode.value = !_isDeleteMode.value
        historyRepository.toggleDeleteMode(_isDeleteMode.value)
    }

    fun initHistoryPokemons() {
        _isDeleteMode.value = false
        historyRepository.initHistoryPokemons()
    }
}