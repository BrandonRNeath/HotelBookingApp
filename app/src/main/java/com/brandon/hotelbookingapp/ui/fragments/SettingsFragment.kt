package com.brandon.hotelbookingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.databinding.SettingsFragmentBinding
import com.brandon.hotelbookingapp.db.model.ApplicationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.settings_fragment) {

    private var binding: SettingsFragmentBinding? = null

    private val applicationViewModel: ApplicationViewModel by navGraphViewModels(R.id.my_nav) { defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SettingsFragmentBinding.inflate(layoutInflater)
        binding!!.settingsScreenTv.setOnClickListener {
            Navigation.findNavController(binding!!.root).navigate(R.id.navigate_to_home)
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()

        private const val TAG = "SettingsFragment"

    }
}
