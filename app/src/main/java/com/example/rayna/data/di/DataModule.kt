package com.example.rayna.data.di

import com.example.rayna.data.repository.LocationRepository
import com.example.rayna.data.repository.LocationRepositoryImpl
import com.example.rayna.data.repository.ProductRepository
import com.example.rayna.data.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideLocationRepository(): LocationRepository {
        return LocationRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository {
        return ProductRepositoryImpl()
    }
}