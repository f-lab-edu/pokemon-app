package com.sdhong.pokemonapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.sdhong.pokemonapp.PokemonApplication
import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.local.repository.HistoryRepository
import kotlinx.coroutines.flow.StateFlow

class HistoryViewModel(
    private val historyRepository: HistoryRepository
) : ViewModel() {

    val historyPokemons: StateFlow<List<Pokemon.History>> = historyRepository.historyPokemons

    fun onPokemonClick(
        position: Int,
        startDetailActivity: (pokemonId: Int) -> Unit
    ) {
        val pokemon = historyPokemons.value[position]

        startDetailActivity(getPokemonId(pokemon.detailUrl))

        historyRepository.removePokemonHistory(pokemon)
        historyRepository.addPokemonHistory(pokemon)
    }

    private fun getPokemonId(url: String): Int {
        return url.split("/")[6].toInt()
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])

                return HistoryViewModel(
                    (application as PokemonApplication).historyRepository
                ) as T
            }
        }
    }
}