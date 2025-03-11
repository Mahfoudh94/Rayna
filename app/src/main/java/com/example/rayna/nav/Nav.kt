package com.example.testrayna.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rayna.presentation.view.AddProductScreen
import com.example.rayna.presentation.view.MainScreen
import com.example.rayna.presentation.viewmodel.LocationViewModel
import com.example.rayna.presentation.viewmodel.ProductViewModel
import com.example.testrayna.Page.Account
import com.example.testrayna.Page.Setting


@Composable
fun Nav(navController: NavHostController) {
    val productViewModel = hiltViewModel<ProductViewModel>()
    val locationViewModel = hiltViewModel<LocationViewModel>()
    NavHost(
         navController = navController,
        startDestination = Screen.Home.route
    ) {
     composable(Screen.Home.route){
         MainScreen(
            productViewModel,
             locationViewModel,
         )
     }

        composable(Screen.scan.route){

        }
        composable(Screen.Account.route){
            Account()
        }
        composable(Screen.Search.route){
           Setting()
        }
        composable(Screen.Setting.route){
            AddProductScreen()
        }
    }
}




