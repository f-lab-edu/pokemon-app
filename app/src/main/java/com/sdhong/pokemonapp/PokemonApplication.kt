package com.sdhong.pokemonapp

import android.app.Application
import com.sdhong.pokemonapp.local.repository.HistoryRepository

class PokemonApplication : Application() {
    val historyRepository: HistoryRepository = HistoryRepository
}