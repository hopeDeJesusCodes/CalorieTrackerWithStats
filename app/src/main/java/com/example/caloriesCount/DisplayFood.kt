package com.example.caloriesCount

import com.example.caloriesCount.data.FoodEntry

data class DisplayFood(
    val foodEntry: String?,
    val calories: String?,
) : java.io.Serializable