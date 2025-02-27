package com.sdhong.pokemonapp.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val next: String,
    val results: List<PokemonListItem>
) {
    @Serializable
    data class PokemonListItem(
        val name: String,
        val url: String
    )
}
