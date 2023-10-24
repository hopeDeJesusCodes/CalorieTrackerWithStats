package com.example.caloriesCount

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FoodApplication : Application() {
    val db by lazy { FoodDatabase.getInstance(this) }
}