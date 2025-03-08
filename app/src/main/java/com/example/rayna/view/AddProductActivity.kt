package com.example.rayna.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class AddProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddProductScreen(
                onProductAdded = {
                    // Handle the logic when the product is added
                    finish() // Closes the activity
                },
                onCancel = {
                    finish() // Closes the activity
                },
                pictureUrl = 1,
            )
        }
    }
}
