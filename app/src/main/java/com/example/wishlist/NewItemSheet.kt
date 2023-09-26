package com.example.wishlist

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.wishlist.databinding.FragmentNewItemSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

class NewItemSheet(var wishItem: WishItem?) : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentNewItemSheetBinding
    private lateinit var itemViewModel: ItemViewModel
    private var dueTime: LocalTime? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (wishItem != null)
        {
            binding.itemTitle.text = "Edit Item"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(wishItem!!.name)
            binding.price.text = editable.newEditable(wishItem!!.price)
        }
        else
        {
            binding.itemTitle.text = "New Item"
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


    private fun saveAction()
    {
        val name = binding.name.text.toString()
        val price = binding.price.text.toString()
        if(wishItem == null)
        {
            val newItem = WishItem(completed = true, name = name, price = price)
            itemViewModel.addWishItem(newItem)
        }
        else
        {
            itemViewModel.updateWishItem(wishItem!!.id, name, price)
        }
        binding.name.setText("")
        binding.price.setText("")
        dismiss()
    }

}