package com.example.wishlist

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.wishlist.R
import java.util.*
class WishItem(
    var name: String,
    var price: String,
    var completed: Boolean,
    var id: UUID = UUID.randomUUID()
)
{
    private fun purple(context: Context) = ContextCompat.getColor(context, androidx.constraintlayout.widget.R.color.abc_secondary_text_material_dark)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)
    fun isCompleted() = completed != null
}