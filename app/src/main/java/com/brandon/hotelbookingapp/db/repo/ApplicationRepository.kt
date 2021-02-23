package com.brandon.hotelbookingapp.db.repo

import androidx.lifecycle.LiveData
import com.brandon.hotelbookingapp.db.model.HotelListing
import com.brandon.hotelbookingapp.db.model.HotelLocations

interface ApplicationRepository {
    fun getHotelListings(): LiveData<List<HotelListing>>
    fun updateHotelListing(favouriteStatus: Boolean, hotelListingId: Int)
    fun getHotelFavourites(): LiveData<List<HotelListing>>
    fun getHotelLocations(): LiveData<List<HotelLocations>>
}
