package com.sdhong.pokemonapp.local.module

import android.content.Context
import androidx.room.Room
import com.sdhong.pokemonapp.local.database.HistoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HistoryDatabaseModule {

    @Singleton
    @Provides
    fun provideHistoryDatabase(@ApplicationContext context: Context): HistoryDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            HistoryDatabase::class.java,
            "history-database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideHistoryDao(database: HistoryDatabase) = database.historyDao()
}