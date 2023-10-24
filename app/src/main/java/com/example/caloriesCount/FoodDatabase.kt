package com.example.caloriesCount

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.caloriesCount.data.FoodEntry
import androidx.room.RoomDatabase

@Database(entities = [FoodEntry::class], version = 1)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodEntryDao(): FoodEntryDao

    companion object {

        @Volatile
        private var INSTANCE: FoodDatabase? = null

        fun getInstance(context: Context): FoodDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FoodDatabase::class.java, "Food-db"
            ).build()
    }
}
