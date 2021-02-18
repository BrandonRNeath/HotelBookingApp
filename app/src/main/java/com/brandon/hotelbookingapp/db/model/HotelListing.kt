package com.brandon.hotelbookingapp.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "hotel_listings_table")
@Parcelize
data class HotelListing(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("hotel_name") val hotelName: String,
    @SerializedName("hotel_image_url") val hotelImageUrl: String,
    @SerializedName("hotel_ranking") val hotelRating: Int
) : Parcelable
