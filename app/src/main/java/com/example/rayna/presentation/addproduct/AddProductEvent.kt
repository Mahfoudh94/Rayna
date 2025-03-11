package com.example.rayna.presentation.addproduct

import androidx.annotation.DrawableRes

//sealed class AddProductEvent {
//    data class NameChanged(val name: String) : AddProductEvent()
//    data class CategorySelected(val category: String) : AddProductEvent()
//    data class DescriptionChanged(val description: String) : AddProductEvent()
//    data class PriceChanged(val price: Double) : AddProductEvent()
//    data class ImageUriChanged(val uri: String) : AddProductEvent()
//    data class RatingChanged(val rating: Double) : AddProductEvent()
//    object Submit : AddProductEvent()
//}

sealed class AddProductEvent {
    data class NameChanged(val name: String) : AddProductEvent()
    data class CategoryChanged(val category: String) : AddProductEvent()
    data class DescriptionChanged(val description: String) : AddProductEvent()
    data class PriceChanged(val price: Double) : AddProductEvent()
    data class ImageUriChanged(val uri: String) : AddProductEvent()
    data class RatingChanged(val rating: Double) : AddProductEvent()
    object Submit : AddProductEvent()
}


