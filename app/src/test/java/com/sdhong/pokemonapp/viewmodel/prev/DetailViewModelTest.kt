package com.sdhong.pokemonapp.viewmodel.prev

import com.sdhong.pokemonapp.local.model.PokemonDetail
import com.sdhong.pokemonapp.viewmodel.prev.fake.FakePokemonApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DetailViewModelTest {

    private val pokemonApi = FakePokemonApi()

    private var pokemonDetail = PokemonDetail(
        name = "",
        imgUrl = "",
        weight = 0,
        height = 0,
        types = emptyList(),
        abilities = emptyList()
    )

    @Test
    fun `포켓몬 id가 1인 포켓몬 상세정보를 가져온다`() {
        pokemonApi.getPokemonDetail(1).also {
            pokemonDetail = PokemonDetail(
                name = it.name,
                imgUrl = it.sprites.imgUrl,
                weight = it.weight,
                height = it.height,
                types = it.types.map { type -> type.type.name },
                abilities = it.abilities.map { ability -> ability.ability.name }
            )
        }
        assertEquals(
            PokemonDetail(
                name = "bulbasaur",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                weight = 69,
                height = 7,
                types = listOf("grass", "poison"),
                abilities = listOf("overgrow", "chlorophyll")
            ),
            pokemonDetail
        )
    }
}