package com.example.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlist.databinding.WishItemCellBinding



class WishItemAdapter(
    private val wishItems: List<WishItem>,
    private val clickListener: MainActivity
): RecyclerView.Adapter<WishItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = WishItemCellBinding.inflate(from, parent, false)
        return WishItemViewHolder(parent.context, binding, clickListener)
    }

    override fun onBindViewHolder(holder: WishItemViewHolder, position: Int) {
        holder.bindWishItem(wishItems[position])
    }

    override fun getItemCount(): Int = wishItems.size
}