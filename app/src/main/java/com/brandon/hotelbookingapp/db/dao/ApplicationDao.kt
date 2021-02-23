package com.brandon.hotelbookingapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brandon.hotelbookingapp.db.model.HotelListing
import com.brandon.hotelbookingapp.db.model.HotelLocations

@Dao
interface ApplicationDao {

    @Query("SELECT * FROM hotel_listings_table WHERE isFavourite = 1")
    fun getHotelFavourites(): LiveData<List<HotelListing>>

    @Query("SELECT * FROM hotel_locations_table")
    fun getHotelLocations(): LiveData<List<HotelLocations>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHotelListings(hotelListings: List<HotelListing>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHotelLocations(hotelListings: List<HotelLocations>)

    @Query("UPDATE hotel_listings_table SET isFavourite= :favouriteStatus WHERE id = :hotelListingId")
    fun updateHotelListing(favouriteStatus: Boolean, hotelListingId: Int)

    @Query("SELECT * FROM hotel_listings_table")
    fun getHotelListings(): LiveData<List<HotelListing>>

    @Query("DELETE FROM hotel_listings_table")
    fun wipeHotelListings()

    @Query("DELETE FROM hotel_locations_table")
    fun wipeHotelLocations()

}
