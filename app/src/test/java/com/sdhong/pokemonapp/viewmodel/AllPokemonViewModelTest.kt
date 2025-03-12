package com.sdhong.pokemonapp.viewmodel

import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.viewmodel.fake.FakeHistoryDao
import com.sdhong.pokemonapp.viewmodel.fake.FakePokemonApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AllPokemonViewModelTest {

    private val pokemonApi = FakePokemonApi()
    private val historyDao = FakeHistoryDao()

    private var allPokemon: List<Pokemon.Normal> = emptyList()

    @BeforeEach
    fun setUp() {
        allPokemon = pokemonApi.getAllPokemon().results.mapIndexed { index, item ->
            Pokemon.Normal(
                uid = index + 1,
                name = item.name,
                imgUrl = pokemonApi.getImgUrl(item.url.split("/")[6].toInt()),
                detailUrl = item.url
            )
        }
    }

    @Test
    fun `포켓몬 목록을 가져온다`() {
        assertEquals(
            listOf(
                Pokemon.Normal(
                    uid = 1,
                    name = "bulbasaur",
                    imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    detailUrl = "https://pokeapi.co/api/v2/pokemon/1/"
                ),
                Pokemon.Normal(
                    uid = 2,
                    name = "ivysaur",
                    imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
                    detailUrl = "https://pokeapi.co/api/v2/pokemon/2/"
                )
            ),
            allPokemon
        )
    }

    @Test
    fun `포켓몬을 클릭하면 클릭한 포켓몬의 조회일이 업데이트된다`() {
        val clickedPokemon = allPokemon.first()
        historyDao.upsert(
            Pokemon.History(
                uid = clickedPokemon.uid,
                name = clickedPokemon.name,
                imgUrl = clickedPokemon.imgUrl,
                detailUrl = clickedPokemon.detailUrl,
                lastViewed = "2025. 3. 9. 오전 10:20:40"
            )
        )
        assertEquals(
            "2025. 3. 9. 오전 10:20:40",
            historyDao.getAll().first().lastViewed
        )
    }
}