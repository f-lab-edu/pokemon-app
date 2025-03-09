package com.sdhong.pokemonapp.repository.di

import com.sdhong.pokemonapp.repository.PokemonRepository
import com.sdhong.pokemonapp.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PokemonRepositoryModule {

    @Binds
    fun bindPokemonRepository(repository: PokemonRepositoryImpl): PokemonRepository
}