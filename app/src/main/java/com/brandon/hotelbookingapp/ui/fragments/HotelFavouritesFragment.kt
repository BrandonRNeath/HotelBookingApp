package com.brandon.hotelbookingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.brandon.hotelbookingapp.adapters.HotelFavouritesAdapter
import com.brandon.hotelbookingapp.databinding.HotelFavouritesFragmentBinding
import com.brandon.hotelbookingapp.db.model.ApplicationViewModel

class HotelFavouritesFragment : Fragment() {

    private var binding: HotelFavouritesFragmentBinding? = null
    private lateinit var applicationViewModel: ApplicationViewModel
    private lateinit var hotelFavouritesAdapter: HotelFavouritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = HotelFavouritesFragmentBinding.inflate(layoutInflater)

        applicationViewModel = ViewModelProvider(this).get(ApplicationViewModel::class.java)

        hotelFavouritesAdapter = HotelFavouritesAdapter(requireContext(), mutableListOf())

        binding!!.hotelFavouritesRecyclerView.adapter = hotelFavouritesAdapter

        binding!!.hotelFavouritesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        applicationViewModel.getHotelFavourites().observe(viewLifecycleOwner, { hotelFavourites ->
            hotelFavouritesAdapter.updateHotelFavourites(hotelFavourites)
        })


        return binding!!.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = HotelFavouritesFragment()

        private const val TAG = "HotelFavouritesFragment"
    }
}