package com.sdhong.pokemonapp.viewmodel

import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class HistoryViewModelTest {

    private val pokemonRepository = mock<PokemonRepository>()
    private val viewModel = HistoryViewModel(pokemonRepository)

    @Test
    fun `test initData`() {
        assertEquals(emptyList<List<Pokemon.History>>(), viewModel.historyPokemons.value)
        assertFalse(viewModel.isDeleteMode.value)
    }

    @Test
    fun `DeleteMode가 true일 때 PokemonClick 테스트`() = runTest {
        val dummyPokemon = Pokemon.History(
            uid = 1,
            name = "pikachu",
            imgUrl = "",
            detailUrl = "",
            lastViewed = "",
            isChecked = false
        )
        val dummyPokemonsFlow = MutableStateFlow(listOf(dummyPokemon))

        Mockito.`when`(viewModel.historyPokemons).thenReturn(dummyPokemonsFlow)

        // TODO: 왜 Fail 되는지? 어떻게 하면 StateFlow를 테스트할 수 있을까?
        assertEquals(listOf(dummyPokemon), viewModel.historyPokemons.value)

        // DeleteMode false -> true
        viewModel.toggleDeleteMode().join()

        viewModel.onPokemonClick(0)
        verify(pokemonRepository).upsert(dummyPokemon.copy(isChecked = true))
    }

    @Test
    fun `DeleteMode가 false일 때 PokemonClick 테스트`() = runTest {
        val dummyPokemon = Pokemon.History(
            uid = 1,
            name = "pikachu",
            imgUrl = "",
            detailUrl = "",
            lastViewed = "",
            isChecked = false
        )
        val dummyPokemonsFlow = MutableStateFlow(listOf(dummyPokemon))

        Mockito.`when`(viewModel.historyPokemons).thenReturn(dummyPokemonsFlow)

        // TODO: 왜 Fail 되는지? 어떻게 하면 StateFlow를 테스트할 수 있을까?
        assertEquals(listOf(dummyPokemon), viewModel.historyPokemons.value)


        viewModel.onPokemonClick(0)

        // TODO: eventFlow에 event send 잘 되었는지 검증

        verify(pokemonRepository).upsert(
            dummyPokemon.copy(
                lastViewed = ""
            )
        )
    }

    @Test
    fun `DeleteMode 토글 테스트`() = runTest {
        // false 였다가 true로 바뀐 케이스 검증
        assertFalse(viewModel.isDeleteMode.value)
        viewModel.toggleDeleteMode().join()
        verify(pokemonRepository).updateDeleteMode(true)
        assertTrue(viewModel.isDeleteMode.value)

        // true 였다가 false로 바뀐 케이스 검증
        viewModel.toggleDeleteMode().join()
        verify(pokemonRepository).deleteChecked()
        verify(pokemonRepository).updateDeleteMode(false)
        assertFalse(viewModel.isDeleteMode.value)
    }
}