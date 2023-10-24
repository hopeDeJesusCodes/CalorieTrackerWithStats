package com.example.caloriesCount

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.caloriesCount.data.FoodEntry
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [FoodEntry::class], version = 2)
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
                FoodDatabase::class.java, "New-Food-db"
            ).addMigrations(MIGRATION_1_2)
                .build()

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create a new table for version 2 with the updated schema
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `food_entries_v2` " +
                            "(`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "`foodName` TEXT, " +
                            "`calories` INTEGER NOT NULL)"
                )

                // Copy data from the old table to the new table while converting 'calories' to INTEGER
                database.execSQL(
                    "INSERT INTO `food_entries_v2` (`id`, `foodName`, `calories`) " +
                            "SELECT `id`, `foodName`, CAST(`calories` AS INTEGER) FROM `food_entries`"
                )

                // Remove the old table
                database.execSQL("DROP TABLE IF EXISTS `food_entries`")

                // Rename the new table to the original name
                database.execSQL("ALTER TABLE `food_entries_v2` RENAME TO `food_entries`")
            }
        }


    }


}
