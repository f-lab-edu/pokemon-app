package com.sdhong.pokemonapp.repository

import com.sdhong.pokemonapp.local.dao.HistoryDao
import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.remote.api.PokemonApi
import com.sdhong.pokemonapp.remote.model.PokemonDetailResponse
import com.sdhong.pokemonapp.remote.model.PokemonListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val historyDao: HistoryDao
) : PokemonRepository {

    override suspend fun getAllPokemon(): PokemonListResponse = pokemonApi.getAllPokemon()

    override suspend fun getPokemonDetail(id: Int): PokemonDetailResponse =
        pokemonApi.getPokemonDetail(id)

    override fun flowHistoryPokemons(): Flow<List<Pokemon.History>> = historyDao.getAll()

    override suspend fun upsert(history: Pokemon.History) {
        historyDao.upsert(history)
    }

    override suspend fun updateDeleteMode(isDeleteMode: Boolean) {
        historyDao.updateDeleteMode(isDeleteMode)
    }

    override suspend fun deleteChecked() {
        historyDao.deleteChecked()
    }

    override suspend fun deleteAll() {
        historyDao.deleteAll()
    }

}