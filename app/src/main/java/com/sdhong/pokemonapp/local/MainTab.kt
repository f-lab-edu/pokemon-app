package com.sdhong.pokemonapp.local

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sdhong.pokemonapp.R

enum class MainTab(
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int
) {
    ALL_POKEMON(titleId = R.string.all_pokemon_tab_title, iconId = R.drawable.ic_pikachu),
    HISTORY(titleId = R.string.history_tab_title, R.drawable.ic_pokeball)
}