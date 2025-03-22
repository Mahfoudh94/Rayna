package com.example.rayna.domain.usecase

import com.example.rayna.data.model.Product
import com.example.rayna.data.repository.ProductRepository
import javax.inject.Inject

class AddProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun invoke(product: Product) = productRepository.addProduct(product)
}