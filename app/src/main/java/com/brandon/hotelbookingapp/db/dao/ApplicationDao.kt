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

    @Query("UPDATE hotel_listings_table SET isFavourite= :favouriteStatus WHERE id = :hotelListingId")
    fun updateHotelListing(favouriteStatus: Boolean, hotelListingId: Int)

    @Query("SELECT * FROM hotel_listings_table")
    fun getHotelListings(): LiveData<List<HotelListing>>

    @Query("SELECT COUNT() from hotel_favourites_table WHERE id =:hotelFavouriteId")
    fun checkHotelFavouriteExistsByCount(hotelFavouriteId: Int): Int

    @Query("DELETE FROM hotel_favourites_table")
    fun wipeHotelFavourites()

    @Query("DELETE FROM hotel_listings_table")
    fun wipeHotelListings()

}
