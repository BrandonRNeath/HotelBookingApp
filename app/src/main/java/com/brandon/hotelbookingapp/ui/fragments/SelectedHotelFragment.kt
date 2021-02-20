package com.brandon.hotelbookingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.brandon.hotelbookingapp.databinding.SelectedHotelFragmentBinding

class SelectedHotelFragment : Fragment() {

    private val args: SelectedHotelFragmentArgs by navArgs()
    private var binding: SelectedHotelFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SelectedHotelFragmentBinding.inflate(layoutInflater)

        binding!!.selectedHotelNameTv.text = args.hotelName

        return binding!!.root

    }

    companion object {
        @JvmStatic
        fun newInstance() = SelectedHotelFragment()

        private const val TAG = "SelectedHotelFragment"
    }
}
