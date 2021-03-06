package com.brandon.hotelbookingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.databinding.HotelLocationFragmentBinding
import com.brandon.hotelbookingapp.db.model.ApplicationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelLocationFragment : Fragment() {

    private val args: HotelLocationFragmentArgs by navArgs()

    private var binding: HotelLocationFragmentBinding? = null

    private val applicationViewModel: ApplicationViewModel by navGraphViewModels(R.id.my_nav) { defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = HotelLocationFragmentBinding.inflate(layoutInflater)
        binding!!.hotelLocationName.text = args.locationName

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = HotelLocationFragment()

        private const val TAG = "HotelLocationFragment"
    }
}
