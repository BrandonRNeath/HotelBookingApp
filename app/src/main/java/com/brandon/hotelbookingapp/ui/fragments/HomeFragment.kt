package com.brandon.hotelbookingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.adapters.HotelListingAdapter
import com.brandon.hotelbookingapp.adapters.HotelLocationsAdapter
import com.brandon.hotelbookingapp.databinding.HomeFragmentBinding
import com.brandon.hotelbookingapp.db.model.ApplicationViewModel
import com.brandon.hotelbookingapp.db.model.HotelListing
import com.brandon.hotelbookingapp.model.HotelLocations
import com.brandon.hotelbookingapp.utils.AppUtils.isWifiAvailable


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(R.layout.home_fragment) {

    private lateinit var applicationViewModel: ApplicationViewModel
    private var binding: HomeFragmentBinding? = null
    private var param1: String? = null
    private var param2: String? = null

    private val mLocations: ArrayList<HotelLocations> = ArrayList()
    private val mHotelListings: ArrayList<HotelListing> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        loadLocationImages()
        applicationViewModel = ViewModelProvider(this).get(ApplicationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = HomeFragmentBinding.inflate(layoutInflater)
        binding!!.locationsRecyclerView.adapter =
            HotelLocationsAdapter(requireContext(), mLocations)

        binding!!.locationsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding!!.hotelListingsRecyclerView.adapter =
            HotelListingAdapter(requireContext(), mHotelListings)

        binding!!.hotelListingsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun loadLocationImages() {
        if (!isWifiAvailable(requireContext())) {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_internet_connection_active_error),
                Toast.LENGTH_LONG
            ).show()
        }
        val locationLondon =
            HotelLocations("https://i.imgur.com/FWGIkLU.jpg", "London")
        val locationJapan =
            HotelLocations("https://i.imgur.com/P9IVqkS.jpg", "Japan")
        val locationNewYork =
            HotelLocations("https://i.imgur.com/xdLQvy2.jpg", "New York")
        val locationVenice =
            HotelLocations("https://i.imgur.com/Zm7BoLJ.jpg", "Venice")
        val locationParis =
            HotelLocations("https://i.imgur.com/5G0SJtn.jpg", "Paris")
        val locationRiyadh =
            HotelLocations("https://i.imgur.com/SKy9VME.jpg", "Riyadh")
        val locationMadrid =
            HotelLocations("https://i.imgur.com/GpWKCt4.jpeg", "Madrid")
        val locationLisbon =
            HotelLocations("https://i.imgur.com/LncsBc3.jpeg", "Lisbon")
        val locationStockholm =
            HotelLocations("https://i.imgur.com/JYMpWf0.jpeg", "Stockholm")
        mLocations.add(locationLondon)
        mLocations.add(locationJapan)
        mLocations.add(locationNewYork)
        mLocations.add(locationVenice)
        mLocations.add(locationParis)
        mLocations.add(locationRiyadh)
        mLocations.add(locationMadrid)
        mLocations.add(locationLisbon)
        mLocations.add(locationStockholm)
        mLocations.shuffle()
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()

        private const val TAG = "HomeFragment"
    }
}
