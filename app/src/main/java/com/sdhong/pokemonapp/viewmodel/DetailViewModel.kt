package com.sdhong.pokemonapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdhong.pokemonapp.local.model.PokemonDetail
import com.sdhong.pokemonapp.remote.module.PokemonApiModule.pokemonApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pokemonId = savedStateHandle["POKEMON_ID"] ?: 0

    private val _pokemonDetail = MutableStateFlow(
        PokemonDetail(
            name = "",
            imgUrl = "",
            weight = 0,
            height = 0,
            types = emptyList(),
            abilities = emptyList()
        )
    )
    val pokemonDetail = _pokemonDetail.asStateFlow()

    init {
        viewModelScope.launch {
            pokemonApi.getPokemonDetail(pokemonId).also {
                _pokemonDetail.value = PokemonDetail(
                    name = it.name,
                    imgUrl = it.sprites.imgUrl,
                    weight = it.weight,
                    height = it.height,
                    types = it.types.map { type -> type.type.name },
                    abilities = it.abilities.map { ability -> ability.ability.name }
                )
            }
        }
    }
}