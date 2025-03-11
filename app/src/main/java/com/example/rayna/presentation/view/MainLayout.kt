package com.example.rayna.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rayna.R
import com.example.rayna.presentation.viewmodel.LocationViewModel
import com.example.rayna.presentation.viewmodel.ProductViewModel
import com.example.testrayna.nav.IconData
import com.example.testrayna.nav.Nav
import com.example.testrayna.nav.Screen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout() {
    var buttomState by remember {
        mutableStateOf( "Home")
    }
    val productViewModel = hiltViewModel<ProductViewModel>()
    val locationViewModel = hiltViewModel<LocationViewModel>()
    val  navController = rememberNavController()

    val  items = listOf(Screen.Home,Screen.Setting ,Screen.scan,Screen.Search,Screen.Account,)


    Scaffold(

        bottomBar = {
            Surface(
                modifier = Modifier
                    .shadow(8.dp, RoundedCornerShape(0.dp))
                    .clip(RoundedCornerShape(topStart =20 .dp, topEnd = 20.dp))
                ,
                color = Color.Gray
            ){
                NavigationBar (
                    containerColor = Color(0xFF0F5FF6),

                    ){
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    items.forEach { screen ->
                        if (screen == Screen.scan) {

                            NavigationBarItem(
                                selected = (currentRoute == screen.route),
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
                                selected = (currentRoute == screen.route),
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
                                    val iconTint = if (currentRoute == screen.route) Color.Black else Color.White
                                    when (screen.icon) {
                                        is IconData.VectorIcon -> {
                                            Icon(
                                                imageVector = screen.icon.imageVector,
                                                contentDescription = screen.title,
                                                modifier = Modifier.size(30.dp),
                                                tint = iconTint,
                                            )
                                        }
                                        is IconData.PainterIcon -> {
                                            Icon(
                                                painter = painterResource(id = screen.icon.resId),
                                                contentDescription = screen.title,
                                                modifier = Modifier.size(30.dp),
                                                tint = iconTint,
                                            )
                                        }
                                    }
                                },
                                label = { Text(text = screen.title, color = Color.White) },
                                alwaysShowLabel = true
                            )
                        }
                    }
                    }
                }

        }



    ){
        Nav( navController = navController)
    }

}



