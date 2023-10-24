package com.example.caloriesCount

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.caloriesCount.databinding.FoodItemCellBinding

class FoodItemViewHolder(
    private val context: Context,
    private val binding: FoodItemCellBinding,
): RecyclerView.ViewHolder(binding.root)
{

    fun bindWishItem(foodItem: FoodItem)
    {
        binding.foodName.text = foodItem.foodName
        binding.caloriesValue.text = foodItem.calories

    }
}