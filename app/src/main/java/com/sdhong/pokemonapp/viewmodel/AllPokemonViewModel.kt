package com.sdhong.pokemonapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdhong.pokemonapp.local.Pokemons
import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.remote.model.PokemonListResponse.PokemonListItem
import com.sdhong.pokemonapp.remote.module.PokemonApiModule.pokemonApi
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllPokemonViewModel : ViewModel() {

    private val _allPokemon = MutableStateFlow<List<Pokemon.Normal>>(emptyList())
    val allPokemon = _allPokemon.asStateFlow()

    init {
        viewModelScope.launch {
            val result: List<PokemonListItem> = pokemonApi.getAllPokemon().results
            val imgUrlsDeferred = result.map { item -> getImgUrl(item) }
            val imgUrls = imgUrlsDeferred.awaitAll()

            _allPokemon.value = result.mapIndexed { index, item ->
                Pokemon.Normal(
                    uid = index + 1,
                    name = item.name,
                    imgUrl = imgUrls[index],
                    detailUrl = item.url
                )
            }
        }
    }

    private fun getImgUrl(item: PokemonListItem): Deferred<String> = viewModelScope.async {
        val id = getPokemonId(item.url)
        val imgUrl = pokemonApi.getPokemonDetail(id).sprites.imgUrl
        return@async imgUrl
    }

    fun onPokemonClick(
        position: Int,
        addPokemonHistory: (Pokemon) -> Unit,
        startDetailActivity: (pokemonId: Int) -> Unit
    ) {
        val pokemon = _allPokemon.value[position]
        Pokemons.historyPokemons.find { it.uid == pokemon.uid }?.let {
            Pokemons.historyPokemons.remove(it)
        }
        addPokemonHistory(pokemon)
        startDetailActivity(getPokemonId(pokemon.detailUrl))
    }

    private fun getPokemonId(url: String): Int {
        return url.split("/")[6].toInt()
    }
}