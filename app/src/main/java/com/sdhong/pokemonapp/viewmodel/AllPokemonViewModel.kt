package com.sdhong.pokemonapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdhong.pokemonapp.model.Pokemon
import com.sdhong.pokemonapp.model.PokemonListResponse.PokemonListItem
import com.sdhong.pokemonapp.remote.RetrofitClient
import com.sdhong.pokemonapp.remote.api.PokemonApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllPokemonViewModel : ViewModel() {

    private val retrofit = RetrofitClient.retrofit.create(PokemonApi::class.java)

    private val _allPokemon = MutableStateFlow<List<Pokemon.Normal>>(emptyList())
    val allPokemon = _allPokemon.asStateFlow()

    fun getAllPokemon() = viewModelScope.launch {
        val result: List<PokemonListItem> = retrofit.getAllPokemon().results
        _allPokemon.value = result.mapIndexed { index, item ->
            Pokemon.Normal(
                id = index,
                name = item.name,
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${
                    item.url.split(
                        "/"
                    ).toTypedArray()[6]
                }.png"
            )
        }
    }
}