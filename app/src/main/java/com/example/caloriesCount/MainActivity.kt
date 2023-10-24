package com.example.caloriesCount

import StatsFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caloriesCount.databinding.ActivityMainBinding
import androidx.lifecycle.observe
import com.example.caloriesCount.data.FoodEntry
import com.google.android.material.bottomnavigation.BottomNavigationView
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

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_stats -> setCurrentFragment(StatsFragment())
                R.id.menu_foods -> setCurrentFragment(FoodFragment())
            }
            true
        }

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        binding.newFoodButton.setOnClickListener {
            NewItemSheet(null).show(supportFragmentManager, "newItemTag")

            // Perform database operations (for example, clearing and inserting data)
            setCurrentFragment(FoodFragment())

        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }


    // Function to perform database operations
    private fun updateDatabase() {
        val foodEntryDao = (application as FoodApplication).db.foodEntryDao()

        // Perform database operations on a background thread
        GlobalScope.launch(Dispatchers.IO) {
            // Delete all entries from the database
            foodEntryDao.deleteAll()
        }
    }


    private fun insertNewItemToDatabase(newItem: FoodItem) {
        val foodEntry = FoodEntry(foodName = newItem.foodName, calories = newItem.calories)
        val foodEntryDao = (application as FoodApplication).db.foodEntryDao()

        // Insert the new entry into the database
        foodEntryDao.insertAll(listOf(foodEntry))
    }

}
