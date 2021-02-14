package com.brandon.hotelbookingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.adapters.HotelLocationsAdapter
import com.brandon.hotelbookingapp.databinding.HomeFragmentBinding
import com.brandon.hotelbookingapp.model.HotelLocations
import com.brandon.hotelbookingapp.utils.AppUtils.isWifiAvailable
import dagger.hilt.android.AndroidEntryPoint


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private var binding: HomeFragmentBinding? = null
    private var param1: String? = null
    private var param2: String? = null

    private val mLocations: ArrayList<HotelLocations> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        loadLocationImages()
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

        binding!!.homeScreenTv.setOnClickListener {
            Navigation.findNavController(binding!!.root).navigate(R.id.navigate_to_hotel_location)
        }

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
        mLocations.add(locationLondon)
        mLocations.add(locationJapan)
        mLocations.add(locationNewYork)
        mLocations.add(locationVenice)
        mLocations.add(locationParis)
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()

        private const val TAG = "HomeFragment"
    }
}
