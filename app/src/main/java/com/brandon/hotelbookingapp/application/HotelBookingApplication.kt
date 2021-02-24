package com.brandon.hotelbookingapp.application

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.brandon.hotelbookingapp.BuildConfig
import com.brandon.hotelbookingapp.workers.PopulateHotelListingTable
import com.brandon.hotelbookingapp.workers.PopulateHotelLocationsTable
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class HotelBookingApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        plantTimberTree()
        prePopulateHotelAppDb()
    }

    private fun prePopulateHotelAppDb() {
        val populateHotelListingTable =
            OneTimeWorkRequestBuilder<PopulateHotelListingTable>().build()
        val populateHotelLocationsTable =
            OneTimeWorkRequestBuilder<PopulateHotelLocationsTable>().build()

        WorkManager.getInstance(applicationContext)
            .beginWith(populateHotelListingTable)
            .then(populateHotelLocationsTable)
            .enqueue()
    }

    private fun plantTimberTree() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(hiltWorkerFactory).build()
    }
}
