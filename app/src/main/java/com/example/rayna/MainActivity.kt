package com.example.rayna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.example.rayna.presentation.view.MainLayout
import com.example.rayna.presentation.viewmodel.ProductViewModel
import com.example.rayna.ui.theme.RaynaTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                RaynaTheme {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        val productViewModel: ProductViewModel = hiltViewModel()


                        MainLayout(navController = navController, productViewModel = productViewModel)
                    }
                }
            }
        }
    }
}
//last task of app mobile
