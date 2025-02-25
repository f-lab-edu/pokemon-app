package com.sdhong.pokemonapp.model

sealed interface Pokemon {
    val id: Int
    val name: String
    val imgUrl: String
    val viewType: Int

    data class Normal(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val viewType: Int = TYPE_NORMAL
    ) : Pokemon

    data class History(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val viewType: Int = TYPE_HISTORY,
        val lastViewed: String
    ) : Pokemon

    companion object {
        const val TYPE_NORMAL = 1_000
        const val TYPE_HISTORY = 2_000
    }
}
