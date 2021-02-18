package com.brandon.hotelbookingapp.db.database

import android.content.Context
import android.content.res.Resources
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.db.dao.ApplicationDao
import com.brandon.hotelbookingapp.db.model.HotelFavourite
import com.brandon.hotelbookingapp.db.model.HotelListing
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [HotelListing::class, HotelFavourite::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun hotelApplicationDao(): ApplicationDao

    private class ApplicationDatabaseCallback(
        private val scope: CoroutineScope,
        private val resources: Resources
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val hotelApplicationDao = database.hotelApplicationDao()
                    populateWithHotelListings(hotelApplicationDao)
                }
            }
        }

        private suspend fun populateWithHotelListings(applicationDao: ApplicationDao) {
            val jsonString = resources.openRawResource(R.raw.hotels).bufferedReader().use {
                it.readText()
            }
            val typeToken = object : TypeToken<List<HotelListing>>() {}.type
            val hotelListings = Gson().fromJson<List<HotelListing>>(jsonString, typeToken)
            applicationDao.insertHotelListings(hotelListings)
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope, resources: Resources): ApplicationDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    ApplicationDatabase::class.java,
                    "hotel_app_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(ApplicationDatabaseCallback(coroutineScope, resources))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
