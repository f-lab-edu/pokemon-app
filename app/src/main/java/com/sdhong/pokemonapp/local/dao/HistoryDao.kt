package com.sdhong.pokemonapp.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sdhong.pokemonapp.local.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM History ORDER BY lastViewed DESC")
    fun getAll(): Flow<List<Pokemon.History>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: Pokemon.History)

    @Delete
    suspend fun delete(history: Pokemon.History)
}