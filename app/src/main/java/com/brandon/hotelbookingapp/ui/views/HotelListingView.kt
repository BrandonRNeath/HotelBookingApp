package com.brandon.hotelbookingapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.brandon.hotelbookingapp.databinding.HotelListingViewBinding

class HotelListingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val binding = HotelListingViewBinding.inflate(LayoutInflater.from(context))

    companion object {
        private const val TAG = "HotelListingView"
    }
}
