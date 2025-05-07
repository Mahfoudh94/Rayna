package com.example.rayna.presentation.view

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.testrayna.nav.Screen
import com.example.testrayna.nav.IconData

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Screen.Home,
        Screen.Add,
        Screen.Account
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                },
                icon = {
                    when (val icon = screen.icon) {
                        is IconData.VectorIcon ->
                            Icon(imageVector = icon.imageVector, contentDescription = screen.title)
                        is IconData.PainterIcon ->
                            Icon(painter = painterResource(id = icon.resId), contentDescription = screen.title)
                    }
                },
                label = { Text(text = screen.title) }
            )
        }
    }
}
//last task of app mobile__
