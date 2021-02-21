package com.brandon.hotelbookingapp.application

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.brandon.hotelbookingapp.workers.PopulateHotelListingTable
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class HotelBookingApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        prePopulateHotelListings()
    }

    private fun prePopulateHotelListings() {
        val populateHotelListingTable = OneTimeWorkRequestBuilder<PopulateHotelListingTable>().build()
        WorkManager.getInstance(applicationContext)
            .beginWith(populateHotelListingTable)
            .enqueue()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(hiltWorkerFactory).build()
    }
}
