package com.sdhong.pokemonapp.viewmodel.fake

import com.sdhong.pokemonapp.local.model.Pokemon

class FakeHistoryDao {
    private var historyPokemons = listOf(
        Pokemon.History(
            uid = 1,
            name = "bulbasaur",
            imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            detailUrl = "https://pokeapi.co/api/v2/pokemon/1/",
            lastViewed = "2025. 3. 8. 오후 10:10:50"
        ),
        Pokemon.History(
            uid = 2,
            name = "ivysaur",
            imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
            detailUrl = "https://pokeapi.co/api/v2/pokemon/2/",
            lastViewed = "2025. 3. 8. 오후 10:20:05"
        ),
        Pokemon.History(
            uid = 3,
            name = "venusaur",
            imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
            detailUrl = "https://pokeapi.co/api/v2/pokemon/3/",
            lastViewed = "2025. 3. 8. 오후 10:23:07"
        )
    )

    fun getAll(): List<Pokemon.History> {
        return historyPokemons.sortedByDescending { it.lastViewed }
    }

    fun upsert(history: Pokemon.History) {
        historyPokemons.find {
            it.uid == history.uid
        }?.let {
            historyPokemons = historyPokemons.map {
                if (it.uid == history.uid) {
                    history
                } else {
                    it
                }
            }
        } ?: run {
            historyPokemons += history
        }
    }

    fun updateDeleteMode(isDeleteMode: Boolean) {
        historyPokemons = historyPokemons.map {
            it.copy(isDeleteMode = isDeleteMode)
        }
    }

    fun deleteChecked() {
        historyPokemons = historyPokemons.filter { !it.isChecked }
    }

    fun deleteAll() {
        historyPokemons = emptyList()
    }
}