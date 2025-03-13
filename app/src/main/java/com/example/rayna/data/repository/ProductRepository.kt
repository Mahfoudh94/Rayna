package com.example.rayna.data.repository

import com.example.rayna.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<List<Product>>

    suspend fun addProduct(product: Product)


}
