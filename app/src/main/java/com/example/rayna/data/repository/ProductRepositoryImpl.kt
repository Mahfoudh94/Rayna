package com.example.rayna.data.repository

import com.example.rayna.R
import com.example.rayna.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class ProductRepositoryImpl : ProductRepository {
    private val _ProductFlow = MutableSharedFlow<List<Product>>(replay = 1)
    override fun getProducts(): Flow<List<Product>> =_ProductFlow

    override suspend fun addProduct(product: Product) {

    }
}
