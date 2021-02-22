package com.brandon.hotelbookingapp.db.repo

import androidx.lifecycle.LiveData
import com.brandon.hotelbookingapp.db.model.HotelFavourite
import com.brandon.hotelbookingapp.db.model.HotelListing
import com.brandon.hotelbookingapp.db.model.HotelLocations

interface ApplicationRepository {
    fun addHotelFavourite(hotelFavourite: HotelFavourite)
    fun deleteHotelFavourite(hotelFavourite: HotelFavourite)
    fun getHotelListings(): LiveData<List<HotelListing>>
    fun updateHotelListing(favouriteStatus: Boolean, hotelListingId: Int)
    fun checkHotelFavouriteExists(hotelFavouriteId: Int): Boolean
    fun getHotelFavourites(): LiveData<List<HotelFavourite>>
    fun getHotelLocations(): LiveData<List<HotelLocations>>
}
