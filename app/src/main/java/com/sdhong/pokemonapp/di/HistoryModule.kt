package com.sdhong.pokemonapp.di

import com.sdhong.pokemonapp.local.repository.HistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HistoryModule {

    @Singleton
    @Provides
    fun provideHistoryRepository(): HistoryRepository = HistoryRepository
}