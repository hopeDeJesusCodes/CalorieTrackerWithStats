package com.example.caloriesCount

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.caloriesCount.data.FoodEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application){
    // Use LiveData with a List type
    val foodItemsLiveData = MutableLiveData<List<FoodItem>>()
    private val foodEntryDao: FoodEntryDao = FoodDatabase.getInstance(application).foodEntryDao()

    init {
        foodItemsLiveData.value = mutableListOf()
    }

    fun addWishItem(newItem: FoodItem) {
        val list = foodItemsLiveData.value?.toMutableList() ?: mutableListOf()
        list.add(newItem)
        foodItemsLiveData.postValue(list)
        // Print/log the contents of foodItemsLiveData
        list.forEach { item ->
            println("Added Food Item: ${item.foodName}, Calories: ${item.calories}")
        }
        insertNewItemToDatabase(newItem)

    }
    fun insertNewItemToDatabase(newItem: FoodItem) {
        val foodEntry = FoodEntry(foodName = newItem.foodName, calories = newItem.calories)

        // Use a coroutine to perform the database insertion on a background thread
        GlobalScope.launch(Dispatchers.IO) {
            val foodEntryDao = (getApplication() as FoodApplication).db.foodEntryDao()
            foodEntryDao.insertAll(listOf(foodEntry))
        }
    }




    fun getAvgCalories(): LiveData<Float> {
        return foodEntryDao.getAvgCalories()
    }

    fun getMinCalories(): LiveData<Int> {
        return foodEntryDao.getMinCalories()
    }

    fun getMaxCalories(): LiveData<Int> {
        return foodEntryDao.getMaxCalories()
    }





}
