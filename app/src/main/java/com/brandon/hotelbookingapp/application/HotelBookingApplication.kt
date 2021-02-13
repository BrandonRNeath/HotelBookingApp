package com.brandon.hotelbookingapp.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HotelBookingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}
