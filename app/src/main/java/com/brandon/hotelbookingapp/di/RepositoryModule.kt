package com.brandon.hotelbookingapp.di

import com.brandon.hotelbookingapp.db.repo.ApplicationRepository
import com.brandon.hotelbookingapp.db.repo.ApplicationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepository(impl: ApplicationRepositoryImpl): ApplicationRepository
}
