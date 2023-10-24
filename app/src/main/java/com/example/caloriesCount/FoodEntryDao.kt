package com.example.caloriesCount

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.caloriesCount.data.FoodEntry
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface FoodEntryDao {
    @Query("SELECT * FROM food_entries")
    fun getAll(): LiveData<List<FoodEntry>>

    @Insert
    fun insertAll(articles: List<FoodEntry>)

    @Query("DELETE FROM food_entries")
    fun deleteAll()
}