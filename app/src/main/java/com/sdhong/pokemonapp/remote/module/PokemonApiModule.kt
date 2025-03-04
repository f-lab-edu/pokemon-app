package com.sdhong.pokemonapp.remote.module

import com.sdhong.pokemonapp.remote.RetrofitClient
import com.sdhong.pokemonapp.remote.api.PokemonApi

object PokemonApiModule {

    val pokemonApi: PokemonApi = RetrofitClient.retrofit.create(PokemonApi::class.java)
}