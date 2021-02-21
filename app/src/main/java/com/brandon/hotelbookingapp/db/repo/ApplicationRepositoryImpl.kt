package com.brandon.hotelbookingapp.db.repo

import androidx.lifecycle.LiveData
import com.brandon.hotelbookingapp.db.dao.ApplicationDao
import com.brandon.hotelbookingapp.db.model.HotelFavourite
import com.brandon.hotelbookingapp.db.model.HotelListing
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(private val applicationDao: ApplicationDao) : ApplicationRepository {

    override fun addHotelFavourite(hotelFavourite: HotelFavourite) {
        applicationDao.addHotelFavourite(hotelFavourite)
    }

    override fun deleteHotelFavourite(hotelFavourite: HotelFavourite) {
        applicationDao.deleteHotelFavourite(hotelFavourite)
    }

    override fun getHotelListings(): LiveData<List<HotelListing>> {
        return applicationDao.getHotelListings()
    }

    override fun updateHotelListing(favouriteStatus: Boolean, hotelListingId: Int) {
        applicationDao.updateHotelListing(favouriteStatus, hotelListingId)
    }

    override fun checkHotelFavouriteExists(hotelFavouriteId: Int): Boolean {
        return applicationDao.checkHotelFavouriteExistsByCount(hotelFavouriteId) == 1
    }

    override fun getHotelFavourites(): LiveData<List<HotelFavourite>> {
        return applicationDao.getHotelFavourites()
    }
}
