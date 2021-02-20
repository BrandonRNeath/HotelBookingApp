package com.brandon.hotelbookingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.brandon.hotelbookingapp.databinding.HotelLocationFragmentBinding

class HotelLocationFragment : Fragment() {

    private val args: HotelLocationFragmentArgs by navArgs()

    private var binding: HotelLocationFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = HotelLocationFragmentBinding.inflate(layoutInflater)
        binding!!.hotelLocationName.text = args.locationName

        return binding!!.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = HotelLocationFragment()

        private const val TAG = "HotelLocationFragment"
    }
}