package com.example.rayna.presentation.addproduct

import androidx.annotation.DrawableRes

//data class AddProductState(
//    val name: String = "",
//    val category: String = "",
//    val description: String = "",
//    val price: Double = 0.0,
//    val imageUri: String = "",
//    val rating: Double = 0.0
//)
data class AddProductState(
    val name: String = "",
    val category: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val imageUri: String = "",
    val rating: Double = 0.0,
    val error: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)
//last task of app mobile____