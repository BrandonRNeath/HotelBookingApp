package com.brandon.hotelbookingapp.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brandon.hotelbookingapp.db.dao.ApplicationDao
import com.brandon.hotelbookingapp.db.model.HotelListing
import com.brandon.hotelbookingapp.db.model.HotelLocations


@Database(entities = [HotelListing::class, HotelLocations::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun hotelApplicationDao(): ApplicationDao
}
