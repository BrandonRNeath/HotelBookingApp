package com.brandon.hotelbookingapp.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.databinding.ActivityMainBinding
import com.brandon.hotelbookingapp.db.model.ApplicationViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var applicationViewModel: ApplicationViewModel
    private var binding: ActivityMainBinding? = null
    private lateinit var navigationController: NavController
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        navigationController = findNavController(R.id.fragment)
        binding!!.navigationView.setupWithNavController(navigationController)

        appBarConfig = AppBarConfiguration(navigationController.graph, binding!!.drawerLayout)
        setupActionBarWithNavController(navigationController, appBarConfig)

        listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment) {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.teal_200)))
            } else if(destination.id == R.id.settingsFragment) {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.purple_200)))
            }
        }

        applicationViewModel = ViewModelProvider(this).get(ApplicationViewModel::class.java)
        applicationViewModel.getHotelListings().observe(this, { hotels ->
            Log.d(TAG, hotels.toString())
        })
    }

    private fun setupUI() {

    }

    override fun onResume() {
        super.onResume()
        navigationController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navigationController.removeOnDestinationChangedListener(listener)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navigationController = findNavController(R.id.fragment)
        return navigationController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
