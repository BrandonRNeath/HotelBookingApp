package com.brandon.hotelbookingapp.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotel_favourites_table")
data class HotelFavourite(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val hotelName: String,
    @ColumnInfo val hotelImageUrl: String,
    @ColumnInfo val hotelRating: Int
)
