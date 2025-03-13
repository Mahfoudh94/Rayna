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
    val error: String? = null,        // لعرض أي خطأ يحصل
    val isLoading: Boolean = false,   // لعرض مؤشر التحميل
    val isSuccess: Boolean = false    // لمعرفة إن تمت العملية بنجاح
)