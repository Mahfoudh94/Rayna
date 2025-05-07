package com.example.rayna.domain.usecase

import com.example.rayna.data.repository.LocationRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(private val locationRepository: LocationRepository) {
    operator fun invoke() = locationRepository.getLocations()
}