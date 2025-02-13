package com.example.rayna.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rayna.model.Product
import com.example.rayna.repository.LocationRepository
import com.rayna.data.model.Location
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


data class LocationUIState(
    val isLoading: Boolean = false,
    val locations: List<Location> = emptyList(),
    val error: String? = null
)

class LocationViewModel() : ViewModel() {

    private val _locationUiState = MutableStateFlow(LocationUIState())
    val locationUiState: StateFlow<LocationUIState> = _locationUiState.asStateFlow()

    init {
        getLocations()
    }

    fun getLocations() {
        viewModelScope.launch {
            _locationUiState.value = _locationUiState.value.copy(isLoading = true)
            try {
                delay(2000)
                val locations = LocationRepository.getLocations()
                _locationUiState.value = _locationUiState.value.copy(locations = locations, isLoading = false)
            } catch (e: Exception) {
                _locationUiState.value = _locationUiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }
}
