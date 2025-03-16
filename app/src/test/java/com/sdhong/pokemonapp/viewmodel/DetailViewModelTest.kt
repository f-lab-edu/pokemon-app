package com.sdhong.pokemonapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sdhong.pokemonapp.common.IntentExtraKey
import com.sdhong.pokemonapp.local.model.PokemonDetail
import com.sdhong.pokemonapp.remote.model.PokemonDetailResponse
import com.sdhong.pokemonapp.repository.PokemonRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class DetailViewModelTest {

    private val savedStateHandle = mock<SavedStateHandle>()
    private val pokemonRepository = mock<PokemonRepository>()
    private val viewModel = DetailViewModel(savedStateHandle, pokemonRepository)

    @Test
    fun `초기 데이터 확인`() {
        assertEquals(PokemonDetail.DEFAULT, viewModel.pokemonDetail.value)
    }

    @Test
    fun `init 블록 테스트`() = runTest {
        Mockito.`when`(savedStateHandle.get<Int>(IntentExtraKey.POKEMON_ID)).thenReturn(1)
        assertEquals(1, savedStateHandle[IntentExtraKey.POKEMON_ID])

        Mockito.`when`(pokemonRepository.getPokemonDetail(savedStateHandle[IntentExtraKey.POKEMON_ID] ?: 0)).thenReturn(
            PokemonDetailResponse(
                name = "name",
                sprites = PokemonDetailResponse.Sprites(imgUrl = "imgUrl"),
                weight = 1,
                height = 1,
                types = emptyList(),
                abilities = emptyList()
            )
        )

        // TODO: getPokemonDetail의 인자로 실제로는 0이 들어가서 자꾸 테스트 실패 -> 수정 필요
        // TODO: 0으로 수정해도 테스트 실패 -> 수정 필요
        verify(pokemonRepository).getPokemonDetail(1)

        assertEquals(
            PokemonDetail(
                name = "name",
                imgUrl = "imgUrl",
                weight = 1,
                height = 1,
                types = emptyList(),
                abilities = emptyList()
            ), viewModel.pokemonDetail.value
        )
    }
}