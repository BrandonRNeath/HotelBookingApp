package com.brandon.hotelbookingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.adapters.HotelFavouritesAdapter
import com.brandon.hotelbookingapp.databinding.HotelFavouritesFragmentBinding
import com.brandon.hotelbookingapp.db.model.ApplicationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelFavouritesFragment : Fragment() {

    private var binding: HotelFavouritesFragmentBinding? = null
    private lateinit var hotelFavouritesAdapter: HotelFavouritesAdapter

    private val applicationViewModel: ApplicationViewModel by navGraphViewModels(R.id.my_nav) { defaultViewModelProviderFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applicationViewModel.getHotelFavourites().observe(viewLifecycleOwner, { hotelFavourites ->
            hotelFavouritesAdapter.updateHotelFavourites(hotelFavourites)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = HotelFavouritesFragmentBinding.inflate(layoutInflater)

        hotelFavouritesAdapter = HotelFavouritesAdapter(requireContext(), mutableListOf())

        binding!!.hotelFavouritesRecyclerView.adapter = hotelFavouritesAdapter

        binding!!.hotelFavouritesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HotelFavouritesFragment()

        private const val TAG = "HotelFavouritesFragment"
    }
}