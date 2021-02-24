package com.brandon.hotelbookingapp.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.db.database.ApplicationDatabase
import com.brandon.hotelbookingapp.db.model.HotelLocations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class PopulateHotelLocationsTable @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val applicationDatabase: ApplicationDatabase
) : CoroutineWorker(context, workerParameters) {

    companion object {
        private const val TAG = "PopulateHotelListingTable"
    }

    override suspend fun doWork(): Result {
        return try {
            // Reads locations.json then populates Room database with that data
            applicationContext.resources.openRawResource(R.raw.locations).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type = object : TypeToken<List<HotelLocations>>() {}.type
                    val hotelLocations: List<HotelLocations> = Gson().fromJson(jsonReader, type)
                    applicationDatabase.hotelApplicationDao().insertHotelLocations(hotelLocations)
                }
            }
            Result.success()
        } catch (ex: Exception) {
            Timber.e(ex, applicationContext.getString(R.string.populate_hotel_locations_error))
            Result.failure()
        }
    }
}