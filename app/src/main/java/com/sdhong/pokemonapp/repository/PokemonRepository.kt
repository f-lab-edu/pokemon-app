package com.sdhong.pokemonapp.repository

import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.remote.model.PokemonDetailResponse
import com.sdhong.pokemonapp.remote.model.PokemonListResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getAllPokemon(): PokemonListResponse
    suspend fun getPokemonDetail(id: Int): PokemonDetailResponse

    fun flowHistoryPokemons(): Flow<List<Pokemon.History>>
    suspend fun upsert(history: Pokemon.History)
    suspend fun updateDeleteMode(isDeleteMode: Boolean)
    suspend fun deleteChecked()
    suspend fun deleteAll()
}