package com.example.rayna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rayna.ui.theme.RaynaTheme
import com.example.rayna.view.MainScreen
import com.example.rayna.viewmodel.LocationViewModel
import com.example.rayna.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RaynaTheme {
                val productViewModel: ProductViewModel = viewModel()
                val locationViewModel: LocationViewModel = viewModel()

                MainScreen(
                    productViewModel = productViewModel,
                    locationViewModel = locationViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
