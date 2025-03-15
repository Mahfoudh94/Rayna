package com.example.rayna.repository

import com.rayna.data.model.Location
object LocationRepository {
    fun getLocations(): List<Location> {
        return listOf(
            Location(id = "1", name = "Alger", description = "Best store in Algiers", address = "Algiers, Algeria", coordinates = Pair(39.7538, 3.0588), type = "Shop"),
            Location(id = "2", name = "Oran Market", description = "Famous marketplace in Oran", address = "Oran, Algeria", coordinates = Pair(35.6977, -0.6331), type = "Market"),
            Location(id = "3", name = "Constantine Mall", description = "A great shopping mall in Constantine", address = "Constantine, Algeria", coordinates = Pair(36.3650, 6.6147), type = "Mall")
        )
    }
}