package com.brandon.hotelbookingapp.db.repo

import androidx.lifecycle.LiveData
import com.brandon.hotelbookingapp.db.dao.ApplicationDao
import com.brandon.hotelbookingapp.db.model.HotelListing
import com.brandon.hotelbookingapp.db.model.HotelLocations
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(private val applicationDao: ApplicationDao) : ApplicationRepository {

    override fun getHotelListings(): LiveData<List<HotelListing>> {
        return applicationDao.getHotelListings()
    }

    override fun updateHotelListing(favouriteStatus: Boolean, hotelListingId: Int) {
        applicationDao.updateHotelListing(favouriteStatus, hotelListingId)
    }

    override fun getHotelFavourites(): LiveData<List<HotelListing>> {
        return applicationDao.getHotelFavourites()
    }

    override fun getHotelLocations(): LiveData<List<HotelLocations>> {
        return applicationDao.getHotelLocations()
    }
}
