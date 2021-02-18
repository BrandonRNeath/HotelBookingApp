package com.brandon.hotelbookingapp.db.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.brandon.hotelbookingapp.db.database.ApplicationDatabase
import com.brandon.hotelbookingapp.db.repo.ApplicationRepository

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {

    private val applicationRepository: ApplicationRepository

    init {
        val applicationDao = ApplicationDatabase
            .getDatabase(application, viewModelScope, application.resources).hotelApplicationDao()
        applicationRepository = ApplicationRepository(applicationDao)
    }

    fun addHotelFavourite(hotelFavourite: HotelFavourite) {
        applicationRepository.addHotelFavourite(hotelFavourite)
    }

    fun deleteHotelFavourite(hotelFavourite: HotelFavourite) {
        applicationRepository.deleteHotelFavourite(hotelFavourite)
    }

    fun getHotelListings() : LiveData<List<HotelListing>> {
        return applicationRepository.getHotelListings()
    }

    fun getHotelFavourites() : LiveData<List<HotelFavourite>> {
        return applicationRepository.getHotelFavourites()
    }
}
