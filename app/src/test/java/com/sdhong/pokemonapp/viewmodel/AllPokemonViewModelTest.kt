package com.sdhong.pokemonapp.viewmodel

import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.remote.model.PokemonListResponse
import com.sdhong.pokemonapp.repository.PokemonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AllPokemonViewModelTest {

    private val pokemonRepository = mock<PokemonRepository>()
    private val viewModel = AllPokemonViewModel(pokemonRepository)

    @Test
    fun `초기 데이터 확인`() {
        assertEquals(emptyList<List<Pokemon.Normal>>(), viewModel.allPokemon.value)
    }

    @Test
    fun `init 블록 테스트`() = runTest {
        verify(pokemonRepository).getAllPokemon()

        // TODO: imgUrlsDeferred.awaitAll()의 결과가 실제로 순서대로인지 검증?
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `포켓몬을 클릭했을 때 테스트`() = runTest {
        val dummyListItem = PokemonListResponse.PokemonListItem(
            name = "bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/"
        )
        val dummyListResponse = PokemonListResponse(next = "", results = listOf(dummyListItem))
        whenever(pokemonRepository.getAllPokemon()).thenReturn(dummyListResponse)

        viewModel.onPokemonClick(0)
        advanceUntilIdle()

        // TODO: eventFlow에 event send 잘 되었는지 검증

        // TODO: pokemonRepository.upsert 호출 검증
        // verify(pokemonRepository).upsert()
    }
}