package com.example.wishlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.util.UUID

class ItemViewModel: ViewModel()
{
    var wishItems = MutableLiveData<MutableList<WishItem>>()

    init {
        wishItems.value = mutableListOf()
    }

    fun addWishItem(newItem: WishItem){
        val list = wishItems.value
        list!!.add(newItem)
        wishItems.postValue(list)
    }

    fun updateWishItem(id: UUID, name: String, price: String){
        val list = wishItems.value
        val item = list!!.find { it.id == id }!!
        item.name = name
        item.price = price
    }

    fun setCompleted(wishItem: WishItem)
    {
        val list = wishItems.value
        val task = list!!.find { it.id == wishItem.id }!!
        wishItems.postValue(list)
    }
}