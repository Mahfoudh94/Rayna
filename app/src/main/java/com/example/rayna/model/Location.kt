package com.rayna.data.model

data class Location(
    val id: String,
    val name: String,
    val description: String,
    val address: String,
    val coordinates: Pair<Double, Double>,
    val type: String,
    val pictureUrl: Int
)
