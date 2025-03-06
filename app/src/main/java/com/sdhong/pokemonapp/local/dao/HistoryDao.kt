package com.sdhong.pokemonapp.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.sdhong.pokemonapp.local.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM History ORDER BY lastViewed DESC")
    fun getAll(): Flow<List<Pokemon.History>>

    @Upsert
    suspend fun upsert(history: Pokemon.History)

    @Query("UPDATE History SET isDeleteMode = :isDeleteMode")
    suspend fun updateDeleteMode(isDeleteMode: Boolean)

    @Query("DELETE FROM History WHERE isChecked = 1")
    suspend fun deleteChecked()

    @Query("DELETE FROM History")
    suspend fun deleteAll()
}