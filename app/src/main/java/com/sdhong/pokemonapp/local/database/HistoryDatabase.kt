package com.sdhong.pokemonapp.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sdhong.pokemonapp.local.dao.HistoryDao
import com.sdhong.pokemonapp.local.model.Pokemon

@Database(entities = [Pokemon.History::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}