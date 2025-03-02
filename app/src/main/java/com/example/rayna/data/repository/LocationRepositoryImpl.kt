package com.example.rayna.data.repository

import com.example.rayna.R
import com.rayna.data.model.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class LocationRepositoryImpl : LocationRepository {

    private val _locations = mutableListOf(
        Location(
            id = "1",
            name = "Shoping",
            description = "City of Lights",
            address = "Paris, France",
            coordinates = Pair(48.8566, 2.3522),
            type = "City",
            pictureUrl = R.drawable.b
        ),
        Location(
            id = "2",
            name = "Prodact",
            description = "Capital of England",
            address = "London, UK",
            coordinates = Pair(51.5074, -0.1278),
            type = "City",
            pictureUrl = R.drawable.d
        ),
        Location(
            id = "3",
            name = "Service",
            description = "The Big Apple",
            address = "New York, USA",
            coordinates = Pair(40.7128, -74.0060),
            type = "City",
            pictureUrl = R.drawable.f
        )
    )

    private val _locationsFlow = MutableSharedFlow<List<Location>>(replay = 1)

    init {
        _locationsFlow.tryEmit(_locations.toList())
    }

    override fun getLocations(): Flow<List<Location>> = _locationsFlow
}
