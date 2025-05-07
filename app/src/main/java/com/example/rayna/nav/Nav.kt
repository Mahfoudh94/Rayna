package com.example.rayna.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rayna.presentation.view.*
import com.example.rayna.presentation.viewmodel.ProductViewModel
import com.example.testrayna.nav.Screen
import com.example.testrayna.Page.ProfileScreen

@Composable
fun Nav(
    navController: NavHostController,
    productViewModel: ProductViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                navController = navController,
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("signup") {
            SignUpScreen(navController = navController)
        }

        composable(Screen.Home.route) {
            MainScreen(productViewModel = productViewModel)
        }

        composable(Screen.Add.route) {
            AddProductScreen(productViewModel = productViewModel)
        }

        composable(Screen.Account.route) {
            ProfileScreen(onLogout = {
                navController.navigate("login") {
                    popUpTo(Screen.Account.route) { inclusive = true }
                }
            })
        }
    }
}
