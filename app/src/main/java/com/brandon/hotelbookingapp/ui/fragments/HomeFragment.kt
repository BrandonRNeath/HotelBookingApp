package com.brandon.hotelbookingapp.ui.fragments

import android.os.Bundle
import android.util.Log
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
import com.brandon.hotelbookingapp.utils.AppUtils
import com.brandon.hotelbookingapp.utils.AppUtils.isWifiAvailable
import dagger.hilt.android.AndroidEntryPoint


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private var binding: HomeFragmentBinding? = null
    private var param1: String? = null
    private var param2: String? = null

    private val mLocationNames: ArrayList<String> = ArrayList()
    private val mLocationImageUrls: ArrayList<String> = ArrayList()

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
            HotelLocationsAdapter(requireContext(), mLocationNames, mLocationImageUrls)

        binding!!.locationsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding!!.homeScreenTv.setOnClickListener {
            Navigation.findNavController(binding!!.root).navigate(R.id.navigate_to_settings)
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
        mLocationImageUrls.add("https://i.imgur.com/FWGIkLU.jpg")
        mLocationNames.add("London")
        mLocationImageUrls.add("https://i.imgur.com/P9IVqkS.jpg")
        mLocationNames.add("Japan")
        mLocationImageUrls.add("https://i.imgur.com/xdLQvy2.jpg")
        mLocationNames.add("New York")
        mLocationImageUrls.add("https://i.imgur.com/Zm7BoLJ.jpg")
        mLocationNames.add("Venice")
        mLocationImageUrls.add("https://i.imgur.com/5G0SJtn.jpg")
        mLocationNames.add("Paris")
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()

        private const val TAG = "HomeFragment"
    }


}
