package com.example.rayna.data.model

import androidx.annotation.DrawableRes

data class Product(
    val id: String,
    val name: String,
    val cat: String,
    val description: String,
    val price: Double,
    @DrawableRes val pictureUrl: Int,
    val rating:Double
)
