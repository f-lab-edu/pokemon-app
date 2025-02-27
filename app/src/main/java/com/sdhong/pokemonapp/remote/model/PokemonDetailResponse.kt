package com.sdhong.pokemonapp.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    val name: String,
    val sprites: Sprites
) {
    @Serializable
    data class Sprites(
        @SerialName("front_default")
        val imgUrl: String
    )
}
