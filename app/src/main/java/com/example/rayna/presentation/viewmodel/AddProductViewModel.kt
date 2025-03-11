package com.example.rayna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rayna.data.model.Product
import com.example.rayna.domain.usecase.AddProductUseCase
import com.example.rayna.presentation.addproduct.AddProductEvent
import com.example.rayna.presentation.addproduct.AddProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddProductState())
    val state: StateFlow<AddProductState> = _state.asStateFlow()


    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(event: AddProductEvent) {
        when (event) {
            is AddProductEvent.NameChanged -> {
                _state.value = _state.value.copy(
                    name = event.name,
                    error = if (event.name.isNotBlank()) null else "اسم المنتج يجب ألا يكون فارغاً"
                )
            }
            is AddProductEvent.CategoryChanged -> {
                _state.value = _state.value.copy(
                    category = event.category,
                    error = if (event.category.isNotBlank()) null else "يجب اختيار الفئة"
                )
            }
            is AddProductEvent.DescriptionChanged -> {
                _state.value = _state.value.copy(description = event.description)
            }
            is AddProductEvent.PriceChanged -> {
                _state.value = _state.value.copy(
                    price = event.price,
                    error = if (event.price > 0) null else "يجب أن يكون السعر أكبر من صفر"
                )
            }
            is AddProductEvent.ImageUriChanged -> {
                _state.value = _state.value.copy(imageUri = event.uri)
            }
            is AddProductEvent.RatingChanged -> {
                _state.value = _state.value.copy(
                    rating = event.rating,
                    error = if (event.rating in 1.0..5.0) null else "التقييم يجب أن يكون بين 1 و 5"
                )
            }
            is AddProductEvent.Submit -> {
                if (canSubmit()) {
                    addProduct()
                } else {
                    _state.value = _state.value.copy(error = "يرجى ملء جميع الحقول بالقيم الصحيحة")
                }
            }
        }
    }

    private fun canSubmit(): Boolean {
        val currentState = _state.value
        return currentState.name.isNotBlank() &&
                currentState.category.isNotBlank() &&
                currentState.price > 0.0 &&
                currentState.rating in 1.0..5.0 &&
                currentState.imageUri.isNotBlank()
    }

    private fun addProduct() {
        val currentState = _state.value
        _state.value = currentState.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                val product = Product(
                    id = UUID.randomUUID().toString(),
                    name = currentState.name,
                    cat = currentState.category,
                    description = currentState.description,
                    price = currentState.price,
                    pictureUrl = currentState.imageUri,
                    rating = currentState.rating
                )
                addProductUseCase(product)
                _state.value = currentState.copy(isSuccess = true, isLoading = false)
            } catch (e: Exception) {
                _state.value = currentState.copy(error = "فشل إضافة المنتج", isLoading = false)
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String) : UiEvent()
        data class Success(val message: String) : UiEvent()
    }
}
