package com.sdhong.pokemonapp.util

fun getPokemonId(url: String): Int = url.split("/")[6].toInt()