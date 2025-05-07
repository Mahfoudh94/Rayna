package com.example.rayna.data.repository

import com.rayna.data.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun getLocations(): Flow<List<Location>>

}
