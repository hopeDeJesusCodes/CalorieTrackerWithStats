package com.example.wishlist

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlist.databinding.WishItemCellBinding

class WishItemViewHolder(
    private val context: Context,
    private val binding: WishItemCellBinding,
    private val clickListener: WishItemClickListener
): RecyclerView.ViewHolder(binding.root)
{

    fun bindWishItem(wishItem: WishItem)
    {
        binding.name.text = wishItem.name

        if (wishItem.isCompleted()){
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }


    }
}