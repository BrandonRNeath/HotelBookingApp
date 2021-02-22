package com.brandon.hotelbookingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.adapters.HotelListingAdapter
import com.brandon.hotelbookingapp.adapters.HotelLocationsAdapter
import com.brandon.hotelbookingapp.databinding.HomeFragmentBinding
import com.brandon.hotelbookingapp.db.model.ApplicationViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private val applicationViewModel: ApplicationViewModel by navGraphViewModels(R.id.my_nav) { defaultViewModelProviderFactory }

    private var binding: HomeFragmentBinding? = null
    private lateinit var hotelListingAdapter: HotelListingAdapter
    private lateinit var hotelLocationsAdapter: HotelLocationsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applicationViewModel.getHotelListings().observe(viewLifecycleOwner, { hotelListings ->
            hotelListingAdapter.updateHotelListings(hotelListings)
        })
        applicationViewModel.getHotelLocations().observe(viewLifecycleOwner, { hotelLocations ->
            hotelLocations.shuffled()
            hotelLocationsAdapter.updateHotelLocations(hotelLocations)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = HomeFragmentBinding.inflate(inflater, container, false)

        hotelLocationsAdapter =
            HotelLocationsAdapter(applicationViewModel, requireContext(), mutableListOf())

        binding!!.locationsRecyclerView.adapter = hotelLocationsAdapter

        binding!!.locationsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        hotelListingAdapter =
            HotelListingAdapter(applicationViewModel, requireContext(), mutableListOf())

        binding!!.hotelListingsRecyclerView.adapter = hotelListingAdapter

        binding!!.hotelListingsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()

        private const val TAG = "HomeFragment"
    }
}
