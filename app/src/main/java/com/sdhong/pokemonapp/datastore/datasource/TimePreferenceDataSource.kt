package com.sdhong.pokemonapp.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TimePreferenceDataSource @Inject constructor(
    private val timeDataStore: DataStore<Preferences>
) {
    object PreferencesKey {
        val LAST_FOREGROUND_TIME = longPreferencesKey("LAST_FOREGROUND_TIME")
    }

    val lastForegroundTime = timeDataStore.data.map { preferences ->
        preferences[PreferencesKey.LAST_FOREGROUND_TIME] ?: 0
    }

    suspend fun updateLastForegroundTime() {
        timeDataStore.edit { preferences ->
            preferences[PreferencesKey.LAST_FOREGROUND_TIME] = System.currentTimeMillis()
        }
    }
}