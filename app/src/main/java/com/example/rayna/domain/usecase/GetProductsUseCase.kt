package com.example.rayna.domain.usecase

import com.example.rayna.data.model.Product
import com.example.rayna.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return repository.getProducts()
    }
}
