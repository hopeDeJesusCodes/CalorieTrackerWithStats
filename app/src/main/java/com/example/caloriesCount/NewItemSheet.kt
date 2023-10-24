package com.example.caloriesCount

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.caloriesCount.databinding.FragmentNewItemSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewItemSheet(var foodItem: FoodItem?) : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentNewItemSheetBinding
    private lateinit var itemViewModel: ItemViewModel
    private var newItemListener: OnNewItemSubmittedListener? = null

    interface OnNewItemSubmittedListener {
        fun onNewItemSubmitted(newItem: FoodItem)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (foodItem != null)
        {
            binding.foodTitle.text = "Edit Item"
            val editable = Editable.Factory.getInstance()
            binding.foodName.text = editable.newEditable(foodItem!!.foodName)
            binding.calories.text = editable.newEditable(foodItem!!.calories)
        }
        else
        {
            binding.foodTitle.text = "Add Food"
        }

        itemViewModel = ViewModelProvider(activity).get(ItemViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewItemSheetBinding.inflate(inflater,container,false)
        return binding.root
    }


    private fun saveAction() {
        val foodName = binding.foodName.text.toString()
        val calories = binding.calories.text.toString()
        if (foodItem == null) {
            val newItem = FoodItem(foodName = foodName, calories = calories)
            itemViewModel.addWishItem(newItem)
        }
        binding.foodName.setText("")
        binding.calories.setText("")
        dismiss()
    }



    fun setOnNewItemSubmittedListener(listener: OnNewItemSubmittedListener) {
        newItemListener = listener
    }

}



