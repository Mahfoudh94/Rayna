package com.example.rayna.model

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val pictureUrl: String,
    val cat: String,
    val rating: Double
)
