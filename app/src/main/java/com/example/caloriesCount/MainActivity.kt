package com.example.caloriesCount

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caloriesCount.databinding.ActivityMainBinding
import androidx.lifecycle.observe
import com.example.caloriesCount.data.FoodEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleApplication = application as FoodApplication
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        binding.newFoodButton.setOnClickListener {
            NewItemSheet(null).show(supportFragmentManager, "newItemTag")

            // Perform database operations (for example, clearing and inserting data)
            updateDatabase()
        }

        setRecyclerView()
    }

    // Function to perform database operations
    private fun updateDatabase() {
        val newItem = FoodItem(foodName = "New Food Name", calories = "100")

        val foodEntryDao = (application as FoodApplication).db.foodEntryDao()

        // Perform database operations on a background thread
        GlobalScope.launch(Dispatchers.IO) {
            // Delete all entries from the database
            foodEntryDao.deleteAll()

            // Insert new entries (if any)
            val newItem = FoodItem(foodName = "New Food Name", calories = "100")
            insertNewItemToDatabase(newItem)
        }
    }


    private fun insertNewItemToDatabase(newItem: FoodItem) {
        val foodEntry = FoodEntry(foodName = newItem.foodName, calories = newItem.calories)
        val foodEntryDao = (application as FoodApplication).db.foodEntryDao()

        // Insert the new entry into the database
        foodEntryDao.insertAll(listOf(foodEntry))
    }

    private fun setRecyclerView() {
        val mainActivity = this
        itemViewModel.foodItemsLiveData.observe(this) { foodItems ->
            binding.foodListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = FoodItemAdapter(foodItems, mainActivity)
            }
        }
    }
}
