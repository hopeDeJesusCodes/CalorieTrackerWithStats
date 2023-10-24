package com.example.caloriesCount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FoodFragment : Fragment() {
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.food_page, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.foodListRecyclerView)
        val mainActivity = activity as MainActivity

        itemViewModel = ViewModelProvider(mainActivity).get(ItemViewModel::class.java)

        itemViewModel.foodItemsLiveData.observe(viewLifecycleOwner) { foodItems ->
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = FoodItemAdapter(foodItems, mainActivity)
        }

        return view
    }
}

