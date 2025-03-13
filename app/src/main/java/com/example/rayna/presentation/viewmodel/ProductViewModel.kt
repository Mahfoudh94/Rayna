package com.example.rayna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rayna.data.model.Product
import com.example.rayna.data.repository.ProductRepository
import com.example.rayna.data.repository.ProductRepositoryImpl
import com.example.rayna.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ProductUIState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class ProductViewModel @Inject constructor(private val getProductsUseCase: GetProductsUseCase) :
    ViewModel() {

    private val _productUiState = MutableStateFlow(ProductUIState())
    val productUiState: StateFlow<ProductUIState> = _productUiState.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _productUiState.value = _productUiState.value.copy(isLoading = true)

            getProductsUseCase().catch {
                _productUiState.value =
                    _productUiState.value.copy(
                        error = "Failed to fetch products",
                        isLoading = false,
                        products = emptyList()
                    )

            }.collect { products ->

                _productUiState.value =
                    _productUiState.value.copy(products = products, isLoading = false, error = null)

            }
        }
    }
}