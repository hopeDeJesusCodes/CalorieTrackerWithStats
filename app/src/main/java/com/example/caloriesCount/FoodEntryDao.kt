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

    @Query("SELECT SUM(calories) FROM food_entries")
    fun getSumCalories(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM food_entries")
    fun getCountCalories(): LiveData<Int>

    @Query("SELECT AVG(calories) FROM food_entries")
    fun getAvgCalories(): LiveData<Float>

    @Query("SELECT MIN(calories) FROM food_entries")
    fun getMinCalories(): LiveData<Int>

    @Query("SELECT MAX(calories) FROM food_entries")
    fun getMaxCalories(): LiveData<Int>

}