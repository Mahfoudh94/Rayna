package com.example.rayna.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rayna.model.Product
import com.example.rayna.repository.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


data class ProductUIState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)

class ProductViewModel() : ViewModel() {

    private val _productUiState = MutableStateFlow(ProductUIState())
    val productUiState: StateFlow<ProductUIState> = _productUiState.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _productUiState.value = _productUiState.value.copy(isLoading = true)
            try {
                delay(2000)
                val products = ProductRepository.getAllProducts()
                _productUiState.value = _productUiState.value.copy(products = products, isLoading = false)
            } catch (e: Exception) {
                _productUiState.value = _productUiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }
}

