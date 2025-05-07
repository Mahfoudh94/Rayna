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
import androidx.compose.material.icons.filled.Add
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
import com.example.rayna.R
import com.example.rayna.presentation.viewmodel.LocationViewModel
import com.example.rayna.presentation.viewmodel.ProductViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout() {
    var buttomState by remember { mutableStateOf("Home") }
    val productViewModel = hiltViewModel<ProductViewModel>()
    val locationViewModel = hiltViewModel<LocationViewModel>()

    Scaffold(
        content = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                when (buttomState) {
                    "Home" -> MainScreen(productViewModel, locationViewModel)
                    "Profile" -> Text(text = "Profile", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    "Nearby Shop" ->Text(text = "Nearby Shop", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    "Add" ->AddProductScreen(productViewModel)
                    "Search" -> Text(text = "Search", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    "Community" -> Text(text = "Community", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    else -> Text(text = "Unknown", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                }
            }
        },
        bottomBar = {
            Surface(
                modifier = Modifier
                    .shadow(8.dp, RoundedCornerShape(0.dp))
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                color = Color.Gray
            ) {
                NavigationBar(containerColor = Color(0xFF4CAF50)) {
                    NavigationBarItem(
                        selected = buttomState == "Home",
                        onClick = { buttomState = "Home" },
                        label = { Text(text = "Home", color = Color.White) },
                        icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null, tint = if (buttomState == "Home") Color.Black else Color.White) }
                    )
                    NavigationBarItem(
                        selected = buttomState == "Nearby Shop",
                        onClick = { buttomState = "Nearby Shop" },
                        label = { Text(text = "Nearby Shop", color = Color.White) },
                        icon = { Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = if (buttomState == "Nearby Shop") Color.Black else Color.White) }
                    )
                    NavigationBarItem(
                        selected = buttomState == "Add",
                        onClick = { buttomState = "Add" },
                        label = { Text(text = "Add Product", color = Color.White) },
                        icon = { Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = if (buttomState == "Add") Color.Black else Color.White) }
                    )
                    NavigationBarItem(
                        selected = buttomState == "Search",
                        onClick = { },
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
                                    contentDescription = "Custom Icon",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        }
                    )
                    NavigationBarItem(
                        selected = buttomState == "Community",
                        onClick = { buttomState = "Community" },
                        label = { Text(text = "Community", maxLines = 1, color = Color.White) },
                        icon = {
                            Image(
                                painter = painterResource(id = R.drawable.a),
                                contentDescription = "Custom Icon",
                                modifier = Modifier.size(30.dp),
                                colorFilter = ColorFilter.tint(if (buttomState == "Community") Color.Black else Color.White)
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = buttomState == "Profile",
                        onClick = { buttomState = "Profile" },
                        label = { Text(text = "Profile", color = Color.White) },
                        icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, tint = if (buttomState == "Profile") Color.Black else Color.White) }
                    )
                }
            }
        }
    )
}
