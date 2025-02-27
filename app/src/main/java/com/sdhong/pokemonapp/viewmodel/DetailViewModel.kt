package com.sdhong.pokemonapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdhong.pokemonapp.remote.module.PokemonApiModule.pokemonApi
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pokemonId = savedStateHandle["POKEMON_ID"] ?: 0

    init {
        viewModelScope.launch {
            pokemonApi.getPokemonDetail(pokemonId)
        }
    }
}