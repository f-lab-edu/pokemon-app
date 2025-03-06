package com.sdhong.pokemonapp.viewmodel

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdhong.pokemonapp.common.Formatter
import com.sdhong.pokemonapp.local.dao.HistoryDao
import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.remote.api.PokemonApi
import com.sdhong.pokemonapp.remote.model.PokemonListResponse.PokemonListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllPokemonViewModel @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val historyDao: HistoryDao
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

    fun onPokemonClick(pokemon: Pokemon.Normal) {
        viewModelScope.launch {
            historyDao.upsert(
                Pokemon.History(
                    uid = pokemon.uid,
                    name = pokemon.name,
                    imgUrl = pokemon.imgUrl,
                    detailUrl = pokemon.detailUrl,
                    lastViewed = Formatter.dateFormat.format(Calendar.getInstance().time)
                )
            )
        }
    }

    private fun getPokemonId(url: String): Int = url.split("/")[6].toInt()
}