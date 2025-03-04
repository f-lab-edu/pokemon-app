package com.sdhong.pokemonapp.local.repository

import com.sdhong.pokemonapp.local.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object HistoryRepository {

    private val _historyPokemons = MutableStateFlow<List<Pokemon.History>>(emptyList())
    val historyPokemons: StateFlow<List<Pokemon.History>> = _historyPokemons.asStateFlow()

    fun initHistoryPokemons() {
        _historyPokemons.value = _historyPokemons.value.map {
            it.copy(isDeleteMode = false, isChecked = false)
        }
    }

    fun toggleDeleteMode(isDeleteMode: Boolean) {
        if (!isDeleteMode) {
            _historyPokemons.value = _historyPokemons.value.toMutableList().also { list ->
                list.removeIf { it.isChecked }
            }
        }
        _historyPokemons.value = _historyPokemons.value.map {
            it.copy(isDeleteMode = isDeleteMode)
        }
    }

    fun updateCheckbox(pokemon: Pokemon.History) {
        _historyPokemons.value = _historyPokemons.value.map {
            if (it.uid == pokemon.uid) {
                it.copy(isChecked = !it.isChecked)
            } else {
                it
            }
        }
    }
}