package com.brandon.hotelbookingapp.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "hotel_locations_table")
@Parcelize
data class HotelLocations(
    @PrimaryKey val id: Int,
    @SerializedName("location_name") val locationName: String,
    @SerializedName("location_image_url") val locationImageUrl: String
) : Parcelable
