package com.example.caloriesCount

import android.app.Application

class FoodApplication : Application() {
    val db by lazy { FoodDatabase.getInstance(this) }
}