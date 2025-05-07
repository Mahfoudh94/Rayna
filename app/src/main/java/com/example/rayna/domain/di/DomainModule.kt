package com.example.rayna.domain.di

import com.example.rayna.data.repository.LocationRepository
import com.example.rayna.data.repository.ProductRepository
import com.example.rayna.domain.usecase.AddProductUseCase
import com.example.rayna.domain.usecase.GetLocationsUseCase
import com.example.rayna.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {


    @Provides
    @Singleton
    fun provideGetLocationsUseCase(locationRepository: LocationRepository): GetLocationsUseCase {
        return GetLocationsUseCase(locationRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(productRepository: ProductRepository): GetProductsUseCase {
        return GetProductsUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun provideAddProductUseCase(productRepository: ProductRepository): AddProductUseCase {
        return AddProductUseCase(productRepository)
    }
}