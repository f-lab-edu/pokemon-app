package com.sdhong.pokemonapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.sdhong.pokemonapp.PokemonApplication
import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.local.repository.HistoryRepository
import com.sdhong.pokemonapp.remote.model.PokemonListResponse.PokemonListItem
import com.sdhong.pokemonapp.remote.module.PokemonApiModule.pokemonApi
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllPokemonViewModel(
    private val historyRepository: HistoryRepository
) : ViewModel() {

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
        startDetailActivity: (pokemonId: Int) -> Unit
    ) {
        val pokemon = _allPokemon.value[position]
        startDetailActivity(getPokemonId(pokemon.detailUrl))

        historyRepository.historyPokemons.value.find { it.uid == pokemon.uid }?.let {
            historyRepository.removePokemonHistory(it)
        }
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

                return AllPokemonViewModel(
                    (application as PokemonApplication).historyRepository
                ) as T
            }
        }
    }
}