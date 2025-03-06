package com.sdhong.pokemonapp.datastore.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {

    private const val TIME_DATASTORE_NAME = "TIME_PREFERENCES"

    private val Context.timeDataStore by preferencesDataStore(name = TIME_DATASTORE_NAME)

    @Provides
    @Singleton
    fun provideTimeDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.timeDataStore
}