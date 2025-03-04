package com.sdhong.pokemonapp.remote.api

import com.sdhong.pokemonapp.remote.model.PokemonDetailResponse
import com.sdhong.pokemonapp.remote.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon")
    suspend fun getAllPokemon(): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: Int
    ): PokemonDetailResponse
}
