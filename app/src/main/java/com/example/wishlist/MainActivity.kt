package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wishlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), WishItemClickListener
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        binding.newItemButton.setOnClickListener {
            NewItemSheet(null).show(supportFragmentManager, "newItemTag")
        }

        setRecyclerView()
    }

    private fun setRecyclerView()
    {
        val mainActivity = this
        itemViewModel.wishItems.observe(this){
            binding.itemListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = WishItemAdapter(it, mainActivity)
            }
        }
    }

    override fun editWishItem(wishItem: WishItem)
    {
        NewItemSheet(wishItem).show(supportFragmentManager,"newItemTag")
    }

    override fun completeWishItem(wishItem: WishItem)
    {
        itemViewModel.setCompleted(wishItem)
    }
}