package com.example.testrayna.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.rayna.R

sealed class IconData {
    data class VectorIcon(val imageVector: ImageVector) : IconData()
    data class PainterIcon(val resId: Int) : IconData()
}

sealed class Screen(val route: String, val title: String, val icon: IconData) {

    object Login : Screen(
        route = "login",
        title = "Login",
        icon = IconData.PainterIcon(R.drawable.a)
    )

    object SignUp : Screen(
        route = "signup",
        title = "Sign Up",
        icon = IconData.PainterIcon(R.drawable.b)
    )

    object Home : Screen(
        route = "Home",
        title = "Home",
        icon = IconData.VectorIcon(Icons.Default.Home)
    )

    object Add : Screen(
        route = "Add",
        title = "Add",
        icon = IconData.VectorIcon(Icons.Default.Add)
    )

    object scan : Screen(
        route = "AddProduct",
        title = "Scan",
        icon = IconData.PainterIcon(R.drawable.c)
    )//

    object Search : Screen(
        route = "communit",
        title = "Search",
        icon = IconData.PainterIcon(R.drawable.a)
    )

    object Account : Screen(
        route = "Account",
        title = "Account",
        icon = IconData.VectorIcon(Icons.Default.AccountCircle)
    )
}
//last task of app mobile____
