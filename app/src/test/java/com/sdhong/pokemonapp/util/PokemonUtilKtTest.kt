package com.sdhong.pokemonapp.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PokemonUtilKtTest {

    @Test
    fun `올바른 url이면 id를 반환한다`() {
        val url = "https://pokeapi.co/api/v2/pokemon/1/"
        val id = getPokemonId(url)
        assertEquals(1, id)
    }

    @Test
    fun `잘못된 url이면 예외가 발생한다`() {
        val url = "https://pokeapi.co/api/v2/pokemon/"
        assertThrows<NumberFormatException> {
            getPokemonId(url)
        }
    }
}