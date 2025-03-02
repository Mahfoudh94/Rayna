package com.example.rayna.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rayna.data.repository.LocationRepositoryImpl
import com.example.rayna.domain.usecase.GetLocationsUseCase
import com.rayna.data.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


data class LocationUIState(
    val isLoading: Boolean = false,
    val locations: List<Location> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class LocationViewModel @Inject constructor(private val getLocationsUseCase: GetLocationsUseCase) :
    ViewModel() {

    private val _locationUiState = MutableStateFlow(LocationUIState())
    val locationUiState: StateFlow<LocationUIState> = _locationUiState.asStateFlow()

    init {
        getLocations()
    }

    fun getLocations() {
        viewModelScope.launch {
            _locationUiState.value = _locationUiState.value.copy(isLoading = true)

            getLocationsUseCase().catch {

                _locationUiState.value =
                    _locationUiState.value.copy(
                        error = "Failed to fetch locations",
                        isLoading = false,
                        locations = emptyList()
                    )

            }.collect { locations ->

                _locationUiState.value = _locationUiState.value.copy(
                    locations = locations,
                    isLoading = false,
                    error = null
                )
            }
        }
    }
}
