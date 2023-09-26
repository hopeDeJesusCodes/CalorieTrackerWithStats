package com.example.wishlist

interface WishItemClickListener
{
    fun editWishItem(wishItem: WishItem)
    fun completeWishItem(wishItem: WishItem)
}