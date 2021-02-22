package com.brandon.hotelbookingapp.db.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.brandon.hotelbookingapp.db.repo.ApplicationRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModel @Inject constructor(private val applicationRepository: ApplicationRepositoryImpl) :
    ViewModel() {

    fun addHotelFavourite(hotelFavourite: HotelFavourite) {
        applicationRepository.addHotelFavourite(hotelFavourite)
    }

    fun deleteHotelFavourite(hotelFavourite: HotelFavourite) {
        applicationRepository.deleteHotelFavourite(hotelFavourite)
    }

    fun getHotelListings(): LiveData<List<HotelListing>> {
        return applicationRepository.getHotelListings()
    }

    fun getHotelLocations(): LiveData<List<HotelLocations>> {
        return applicationRepository.getHotelLocations()
    }

    fun updateHotelListing(favouriteStatus: Boolean, hotelListingId: Int) {
        applicationRepository.updateHotelListing(favouriteStatus, hotelListingId)
    }

    suspend fun checkHotelFavouriteExists(hotelFavouriteId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            applicationRepository.checkHotelFavouriteExists(hotelFavouriteId)
        }
    }

    fun getHotelFavourites(): LiveData<List<HotelFavourite>> {
        return applicationRepository.getHotelFavourites()
    }
}
