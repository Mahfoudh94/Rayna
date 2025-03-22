package com.example.rayna.domain.usecase

import com.example.rayna.data.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val productRepository: ProductRepository) {
    operator fun invoke() = productRepository.getProducts()
}