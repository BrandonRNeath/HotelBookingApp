package com.brandon.hotelbookingapp.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.db.database.ApplicationDatabase
import com.brandon.hotelbookingapp.db.model.HotelListing
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PopulateHotelListingTable @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val applicationDatabase: ApplicationDatabase
) : CoroutineWorker(context, workerParameters) {

   companion object {
       private const val TAG = "PopulateHotelListingTable"
   }

    override suspend fun doWork(): Result {
        return try {
            // Reads hotels.json then populates Room database with that data
            applicationContext.resources.openRawResource(R.raw.hotels).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type = object : TypeToken<List<HotelListing>>() {}.type
                    val hotelListings: List<HotelListing> = Gson().fromJson(jsonReader, type)
                    applicationDatabase.hotelApplicationDao().insertHotelListings(hotelListings)
                }
            }
            Result.success()
        } catch (ex: Exception) {
            Log.d(TAG, applicationContext.getString(R.string.populate_hotel_listing_error))
            Result.failure()
        }
    }
}
