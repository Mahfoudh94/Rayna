package com.example.rayna.presentation.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rayna.domain.usecase.AddProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddProductState())
    val state: StateFlow<AddProductState> = _state

    fun onEvent(event: AddProductEvent) {
        when (event) {
            is AddProductEvent.NameChanged -> {
                _state.update { it.copy(name = event.name) }
            }
            is AddProductEvent.CategorySelected -> {
                _state.update { it.copy(category = event.category) }
            }
            is AddProductEvent.DescriptionChanged -> {
                _state.update { it.copy(description = event.description) }
            }
            is AddProductEvent.PriceChanged -> {
                _state.update { it.copy(price = event.price) }
            }
            is AddProductEvent.ImageUrlChanged -> {
                _state.update { it.copy(pictureUrl = event.url) }
            }
            is AddProductEvent.RatingChanged -> {
                _state.update { it.copy(rating = event.rating) }
            }
            is AddProductEvent.Submit -> {
                if (validateInputs()) {
                    saveProduct()
                } else {
                    // يمكنك إظهار رسالة خطأ هنا
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val currentState = _state.value
        return currentState.name.isNotBlank() &&
                currentState.category.isNotBlank() &&
                currentState.description.isNotBlank() &&
                currentState.price > 0 &&
                currentState.rating in 1.0..5.0
    }

    private fun saveProduct() {
        viewModelScope.launch {
            try {
                addProductUseCase.execute(_state.value)
                println("✅ المنتج تم حفظه بنجاح!")
            } catch (e: Exception) {
                println("❌ خطأ أثناء حفظ المنتج: ${e.message}")
            }
        }
    }
}

// 🛠 State for form fields
data class AddProductState(
    val name: String = "",
    val category: String = "",
    val description: String = "",
    val price: Double = 0.0,
    @DrawableRes val pictureUrl: Int = 0,
    val rating: Double = 0.0
)

// 🎯 Events that can be triggered
sealed class AddProductEvent {
    data class NameChanged(val name: String) : AddProductEvent()
    data class CategorySelected(val category: String) : AddProductEvent()
    data class DescriptionChanged(val description: String) : AddProductEvent()
    data class PriceChanged(val price: Double) : AddProductEvent()
    data class ImageUrlChanged(@DrawableRes val url: Int) : AddProductEvent()
    data class RatingChanged(val rating: Double) : AddProductEvent()
    object Submit : AddProductEvent()
}