package com.sdhong.pokemonapp

import android.app.Application
import com.sdhong.pokemonapp.local.repository.HistoryRepository
import timber.log.Timber

class PokemonApplication : Application() {
    val historyRepository: HistoryRepository by lazy {
        HistoryRepository
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}