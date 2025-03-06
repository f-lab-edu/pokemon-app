package com.sdhong.pokemonapp

import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.sdhong.pokemonapp.datastore.datasource.TimePreferenceDataSource
import com.sdhong.pokemonapp.local.dao.HistoryDao
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class PokemonApplication : Application(), DefaultLifecycleObserver {

    @Inject
    lateinit var historyDao: HistoryDao

    @Inject
    lateinit var timePreferenceDataSource: TimePreferenceDataSource

    override fun onCreate() {
        super<Application>.onCreate()

        Timber.plant(Timber.DebugTree())

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

        ProcessLifecycleOwner.get().lifecycleScope.launch {
            timePreferenceDataSource.lastForegroundTime.collectLatest {
                if (System.currentTimeMillis() - it > 1000 * 60 * 10) {
                    historyDao.deleteAll()
                }
            }
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        ProcessLifecycleOwner.get().lifecycleScope.launch {
            timePreferenceDataSource.updateLastForegroundTime()
        }
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        ProcessLifecycleOwner.get().lifecycle.removeObserver(this)
        super.onDestroy(owner)
    }
}