package com.sdhong.pokemonapp.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    val name: String,
    val sprites: Sprites,
    val weight: Int,
    val height: Int,
    val types: List<Type>,
    val abilities: List<Ability>
) {
    @Serializable
    data class Sprites(
        @SerialName("front_default")
        val imgUrl: String
    )

    @Serializable
    data class Type(
        val type: TypeInfo
    )

    @Serializable
    data class TypeInfo(
        val name: String
    )

    @Serializable
    data class Ability(
        val ability: AbilityInfo
    )

    @Serializable
    data class AbilityInfo(
        val name: String
    )
}
