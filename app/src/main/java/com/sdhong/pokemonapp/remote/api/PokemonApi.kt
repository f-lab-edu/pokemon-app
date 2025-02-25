package com.sdhong.pokemonapp.remote.api

import com.sdhong.pokemonapp.model.PokemonListResponse
import retrofit2.http.GET

interface PokemonApi {

    @GET("pokemon")
    suspend fun getAllPokemon(): PokemonListResponse
}
