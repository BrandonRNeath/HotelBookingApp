package com.brandon.hotelbookingapp.db.repo

import androidx.lifecycle.LiveData
import com.brandon.hotelbookingapp.db.dao.ApplicationDao
import com.brandon.hotelbookingapp.db.model.HotelFavourite
import com.brandon.hotelbookingapp.db.model.HotelListing

class ApplicationRepository(private val applicationDao: ApplicationDao) {

    fun addHotelFavourite(hotelFavourite: HotelFavourite) {
        applicationDao.addHotelFavourite(hotelFavourite)
    }

    fun deleteHotelFavourite(hotelFavourite: HotelFavourite) {
        applicationDao.deleteHotelFavourite(hotelFavourite)
    }

    fun getHotelListings(): LiveData<List<HotelListing>> {
        return applicationDao.getHotelListings()
    }

    fun getHotelFavourites(): LiveData<List<HotelFavourite>> {
        return applicationDao.getHotelFavourites()
    }

}
