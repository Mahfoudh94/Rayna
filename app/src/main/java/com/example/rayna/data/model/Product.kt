package com.example.rayna.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.Keep

@Keep
data class Product(
    val id: String = "",
    val name: String = "",
    val cat: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val pictureUrl: String = "",
    val rating: Double = 0.0
)
