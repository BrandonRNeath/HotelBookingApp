package com.brandon.hotelbookingapp.di

import android.content.Context
import androidx.room.Room
import com.brandon.hotelbookingapp.db.dao.ApplicationDao
import com.brandon.hotelbookingapp.db.database.ApplicationDatabase
import com.brandon.hotelbookingapp.utils.APPLICATION_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideApplicationDao(appDatabase: ApplicationDatabase): ApplicationDao {
        return appDatabase.hotelApplicationDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): ApplicationDatabase {
        return Room.databaseBuilder(
            context,
            ApplicationDatabase::class.java,
            APPLICATION_DB_NAME
        ).build()
    }
}
