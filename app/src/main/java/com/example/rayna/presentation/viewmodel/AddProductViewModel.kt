package com.example.rayna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rayna.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun addProduct(product: Product) {
        _products.value = _products.value + product
    }
}