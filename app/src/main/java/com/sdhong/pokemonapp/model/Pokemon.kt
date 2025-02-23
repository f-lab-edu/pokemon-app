package com.sdhong.pokemonapp.model

sealed interface Pokemon {
    val name: String
    val imgUrl: String
    val viewType: Int

    data class Normal(
        override val name: String,
        override val imgUrl: String,
        override val viewType: Int = TYPE_NORMAL
    ) : Pokemon

    data class Seen(
        override val name: String,
        override val imgUrl: String,
        override val viewType: Int = TYPE_SEEN,
        val lastViewed: String
    ) : Pokemon

    companion object {
        const val TYPE_NORMAL = 1_000
        const val TYPE_SEEN = 2_000
    }
}
