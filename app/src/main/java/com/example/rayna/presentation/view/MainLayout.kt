package com.example.rayna.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.rayna.R
import com.example.rayna.presentation.viewmodel.ProductViewModel
import com.example.rayna.nav.Nav
import com.example.testrayna.nav.Screen
import com.example.testrayna.nav.IconData

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    navController: NavHostController,
    productViewModel: ProductViewModel
) {
    val items = listOf(Screen.Home, Screen.Add, Screen.scan, Screen.Search, Screen.Account)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val noBottomBarRoutes = listOf("login", "signup")

    Scaffold(
        bottomBar = {
            if (currentRoute !in noBottomBarRoutes) {
                Surface(
                    modifier = Modifier
                        .shadow(8.dp, RoundedCornerShape(0.dp))
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                    color = Color.White
                ) {
                    NavigationBar(
                        containerColor = Color(0xFFEEEEEE)
                    ) {
                        items.forEach { screen ->
                            val selected = currentRoute == screen.route
                            val iconTint = if (selected) Color.Black else Color.DarkGray
                            val labelColor = iconTint

                            if (screen == Screen.scan) {
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        Box(
                                            modifier = Modifier
                                                .size(70.dp)
                                                .clip(CircleShape)
                                                .background(Color.White),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.c),
                                                contentDescription = screen.title,
                                                modifier = Modifier.size(50.dp)
                                            )
                                        }
                                    },
                                    alwaysShowLabel = true
                                )
                            } else {
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        when (screen.icon) {
                                            is IconData.VectorIcon -> {
                                                Icon(
                                                    imageVector = screen.icon.imageVector,
                                                    contentDescription = screen.title,
                                                    modifier = Modifier.size(30.dp),
                                                    tint = iconTint
                                                )
                                            }

                                            is IconData.PainterIcon -> {
                                                Icon(
                                                    painter = painterResource(id = screen.icon.resId),
                                                    contentDescription = screen.title,
                                                    modifier = Modifier.size(30.dp),
                                                    tint = iconTint
                                                )
                                            }
                                        }
                                    },
                                    label = {
                                        Text(text = screen.title, color = labelColor)
                                    },
                                    alwaysShowLabel = true
                                )
                            }
                        }
                    }
                }
            }
        }
    ) {
        Nav(navController = navController, productViewModel = productViewModel)
    }
}
//last task of app mobile__