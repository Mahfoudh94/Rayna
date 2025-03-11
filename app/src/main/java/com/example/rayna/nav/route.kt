package com.example.testrayna.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.rayna.R

sealed class IconData {
    data class VectorIcon(val imageVector: ImageVector) : IconData()
    data class PainterIcon(val resId: Int) : IconData()
}

sealed class Screen(val route: String, val title: String, val icon: IconData) {
    object Home : Screen(
        route = "Home",
        title = "Home",
        icon = IconData.VectorIcon(Icons.Default.Home)
    )
    object Setting : Screen(
        route = "Map",
        title = "Map",
        icon = IconData.VectorIcon(Icons.Default.Place)
    )
    object scan : Screen(
        route = "AddProduct",
        title = "Add",
        icon = IconData.PainterIcon(R.drawable.c)
    )
    object Search : Screen(
        route = "communit",
        title = "communit",
        icon = IconData.PainterIcon(R.drawable.a)
    )
    object Account : Screen(
        route = "Account",
        title = "Account",
        icon = IconData.VectorIcon(Icons.Default.AccountCircle)
    )
}
