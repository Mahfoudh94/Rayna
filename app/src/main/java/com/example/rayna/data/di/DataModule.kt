package com.example.rayna.data.di

import com.example.rayna.data.repository.FirebaseProductRepository
import com.example.rayna.data.repository.ProductRepository
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
    fun provideProductRepository(): ProductRepository {
        return FirebaseProductRepository()
    }
}
