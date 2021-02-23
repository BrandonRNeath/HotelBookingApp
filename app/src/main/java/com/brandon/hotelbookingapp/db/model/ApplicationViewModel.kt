package com.brandon.hotelbookingapp.db.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.brandon.hotelbookingapp.db.repo.ApplicationRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModel @Inject constructor(private val applicationRepository: ApplicationRepositoryImpl) :
    ViewModel() {

    fun getHotelListings(): LiveData<List<HotelListing>> {
        return applicationRepository.getHotelListings()
    }

    fun getHotelLocations(): LiveData<List<HotelLocations>> {
        return applicationRepository.getHotelLocations()
    }

    fun updateHotelListing(favouriteStatus: Boolean, hotelListingId: Int) {
        applicationRepository.updateHotelListing(favouriteStatus, hotelListingId)
    }

    fun getHotelFavourites(): LiveData<List<HotelListing>> {
        return applicationRepository.getHotelFavourites()
    }
}
