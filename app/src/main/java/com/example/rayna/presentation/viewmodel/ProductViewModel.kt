package com.example.rayna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rayna.R
import com.example.rayna.data.model.Product
import com.example.rayna.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.util.UUID

data class ProductUIState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class ProductViewModel @Inject constructor(private val getProductsUseCase: GetProductsUseCase) : ViewModel() {

    private val _productUiState = MutableStateFlow(ProductUIState())
    val productUiState: StateFlow<ProductUIState> = _productUiState.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _productUiState.value = _productUiState.value.copy(isLoading = true)

            getProductsUseCase()
                .catch { exception ->
                    _productUiState.value = _productUiState.value.copy(
                        error = exception.message ?: "Failed to fetch products",
                        isLoading = false,
                        products = emptyList()
                    )
                }
                .collect { products ->
                    _productUiState.value = _productUiState.value.copy(
                        products = products,
                        isLoading = false,
                        error = null
                    )
                }
        }
    }

    fun addProduct(name: String, price: String, description: String, imageUri: String, rate: String, category: String) {
        val newProduct = Product(
            id = UUID.randomUUID().toString(),
            name = name,
            price = price.toDoubleOrNull() ?: 0.0,
            description = description,
            cat = category,
            pictureUrl = imageUri,
            rating = rate.toDoubleOrNull() ?: 0.0
        )

        _productUiState.update { currentState ->
            currentState.copy(products = currentState.products + newProduct)
        }
    }

}
