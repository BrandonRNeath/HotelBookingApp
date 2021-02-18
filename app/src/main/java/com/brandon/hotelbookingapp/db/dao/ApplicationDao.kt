package com.brandon.hotelbookingapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.brandon.hotelbookingapp.db.model.HotelFavourite
import com.brandon.hotelbookingapp.db.model.HotelListing

@Dao
interface ApplicationDao {

    @Query("SELECT * FROM hotel_favourites_table")
    fun getHotelFavourites(): LiveData<List<HotelFavourite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addHotelFavourite(vararg hotelFavourite: HotelFavourite)

    @Delete
    fun deleteHotelFavourite(vararg hotelFavourite: HotelFavourite)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHotelListings(hotelListings: List<HotelListing>)

    @Query("SELECT * FROM hotel_listings_table")
    fun getHotelListings(): LiveData<List<HotelListing>>

    @Query("DELETE FROM hotel_favourites_table")
    fun wipeHotelFavourites()

    @Query("DELETE FROM hotel_listings_table")
    fun wipeHotelListings()

}
