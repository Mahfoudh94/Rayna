package com.example.rayna.repository

import com.rayna.data.model.Location

object LocationRepository {
    fun getLocations(): List<Location> {
        return listOf(
            Location(id = "1", name = "Abiidshoping", description = "good", address = "eloued, ALgeira", coordinates = Pair(48.8566, 2.3522), type = "City"),
            Location(id = "2", name = "soltana", description = "normal", address = "eloued, ALgeira", coordinates = Pair(51.5074, -0.1278), type = "City"),
            Location(id = "3", name = "elforsan ", description = "so bad", address = "eloued, ALgeira", coordinates = Pair(40.7128, -74.0060), type = "City") ,
            Location(id = "4", name = "Torkia shoos ", description = "nice", address = "eloued, ALgeira", coordinates = Pair(40.7128, -74.0060), type = "City") ,
            Location(id = "5", name = "Galery", description = "nothing", address = "eloued, ALgeira", coordinates = Pair(40.7128, -74.0060), type = "City") ,
            Location(id = "6", name = " manan_boutique", description = "for kids", address = "elrimal,eloued, ALgeira", coordinates = Pair(40.7128, -74.0060), type = "City") ,
            Location(id = "7", name = "jolina boutique", description = " best dresses", address = "gemar,eloued, ALgeira", coordinates = Pair(40.7128, -74.0060), type = "City") ,
            Location(id = "8", name = "star phone", description = "Their services are excellent, my friends. I recommend them", address = " elnazel_almali,eloued, ALgeira", coordinates = Pair(40.7128, -74.0060), type = "City") ,
            Location(id = "9", name = "yazid_fleuriste", description = "best services best flawers", address = "eloued, ALgeira", coordinates = Pair(40.7128, -74.0060), type = "City") ,
            Location(id = "10", name = "boutique dilan", description = "their prices are very high for average clothing quality", address = "eloued, ALgeira", coordinates = Pair(40.7128, -74.0060), type = "City") ,

        )
    }
}
