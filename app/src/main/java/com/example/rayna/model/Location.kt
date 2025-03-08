package com.rayna.data.model

import androidx.annotation.DrawableRes

data class Location(
    val id: String,
    val name: String,
    val description: String,
    val address: String,
    val coordinates: Pair<Double, Double>,
    val type: String,
    @DrawableRes val pictureUrl: Int
)
