package com.sdhong.pokemonapp.viewmodel.prev

import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.viewmodel.prev.fake.FakeHistoryDao
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HistoryViewModelTest {

    private val historyDao = FakeHistoryDao()

    private var isDeleteMode = false

    @Test
    fun `포켓몬 목록은 최근 조회일 순으로 정렬된다`() {
        val historyPokemons = historyDao.getAll()
        assertEquals(
            listOf(
                FakeHistoryDao.LAST_VIEWED_LATEST,
                FakeHistoryDao.LAST_VIEWED_MIDDLE,
                FakeHistoryDao.LAST_VIEWED_EARLIEST
            ),
            historyPokemons.map { it.lastViewed }
        )
    }

    @Test
    fun `삭제모드일 때 포켓몬을 클릭하면 체크박스가 토글된다`() {
        isDeleteMode = true
        val clickedPokemon = historyDao.getAll().first()
        if (isDeleteMode) {
            historyDao.upsert(clickedPokemon.copy(isChecked = !clickedPokemon.isChecked))
        }
        assertEquals(true, historyDao.getAll().first().isChecked)
    }

    @Test
    fun `삭제모드가 아닐 때 포켓몬을 클릭하면 클릭한 포켓몬의 조회일이 업데이트된다`() {
        isDeleteMode = false
        val clickedPokemon = historyDao.getAll().first()
        if (!isDeleteMode) {
            historyDao.upsert(clickedPokemon.copy(lastViewed = FakeHistoryDao.LAST_VIEWED_UPDATED))
        }
        assertEquals(
            Pokemon.History(
                uid = clickedPokemon.uid,
                name = clickedPokemon.name,
                imgUrl = clickedPokemon.imgUrl,
                detailUrl = clickedPokemon.detailUrl,
                lastViewed = FakeHistoryDao.LAST_VIEWED_UPDATED
            ),
            historyDao.getAll().first()
        )
    }

    @Test
    fun `삭제모드일 때 토글 버튼을 클릭하면 삭제모드가 해제된다`() {
        isDeleteMode = true
        isDeleteMode = !isDeleteMode
        historyDao.updateDeleteMode(isDeleteMode)
        assertEquals(listOf(false, false, false), historyDao.getAll().map { it.isDeleteMode })
    }

    @Test
    fun `삭제모드가 아닐 때 토글 버튼을 클릭하면 삭제모드로 변경된다`() {
        isDeleteMode = false
        isDeleteMode = !isDeleteMode
        historyDao.updateDeleteMode(isDeleteMode)
        assertEquals(listOf(true, true, true), historyDao.getAll().map { it.isDeleteMode })
    }

    @Test
    fun `삭제모드일 때 포켓몬 체크 후 토글 버튼 클릭하면 체크된 항목이 모두 삭제된다`() {
        isDeleteMode = true
        val clickedPokemon = historyDao.getAll().first()
        if (isDeleteMode) {
            historyDao.upsert(clickedPokemon.copy(isChecked = !clickedPokemon.isChecked))
        }
        historyDao.deleteChecked()
        assertEquals(null, historyDao.getAll().find { it.uid == clickedPokemon.uid })
    }
}