package com.example.caloriesCount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel: ViewModel() {
    // Use LiveData with a List type
    val foodItemsLiveData = MutableLiveData<List<FoodItem>>()

    // Expose the LiveData as read-only
    val foodItems: LiveData<List<FoodItem>> get() = foodItemsLiveData

    init {
        foodItemsLiveData.value = mutableListOf()
    }

    fun addWishItem(newItem: FoodItem) {
        val list = foodItemsLiveData.value?.toMutableList() ?: mutableListOf()
        list.add(newItem)
        foodItemsLiveData.postValue(list)
    }

    fun setCompleted(wishItem: FoodItem) {
        val list = foodItemsLiveData.value?.toMutableList() ?: mutableListOf()
        val item = list.find { it.id == wishItem.id }
        item?.foodName = wishItem.foodName
        item?.calories = wishItem.calories
        foodItemsLiveData.postValue(list)
    }
}
