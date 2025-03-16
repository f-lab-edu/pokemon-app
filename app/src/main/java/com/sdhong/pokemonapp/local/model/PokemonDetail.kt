package com.sdhong.pokemonapp.local.model

data class PokemonDetail(
    val name: String,
    val imgUrl: String,
    val weight: Int,
    val height: Int,
    val types: List<String>,
    val abilities: List<String>
) {
    companion object {
        val DEFAULT = PokemonDetail(
            name = "",
            imgUrl = "",
            weight = 0,
            height = 0,
            types = emptyList(),
            abilities = emptyList()
        )
    }
}
