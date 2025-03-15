package com.example.rayna.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.rayna.model.Product
import com.example.rayna.viewmodel.LocationViewModel
import com.example.rayna.viewmodel.ProductViewModel
import com.rayna.data.model.Location

@Composable
fun MainScreen(
    productViewModel: ProductViewModel,
    locationViewModel: LocationViewModel,
    modifier: Modifier = Modifier
) {
    val navItemList = listOf(
        NavItem(label = "Home", icon = Icons.Default.Home, route = "home"),
        NavItem(label = "Nearby Shops", icon = Icons.Default.LocationOn, route = "shops"),
        NavItem(label = "code QR", icon = Icons.Default.LocationOn, route = "code QR"),
        NavItem(label = "community", icon = Icons.Default.Person, route = "community"),
        NavItem(label = "Profile", icon = Icons.Default.Person, route = "profile"),
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(
            productViewModel = productViewModel,
            locationViewModel = locationViewModel,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContentScreen(
    productViewModel: ProductViewModel,
    locationViewModel: LocationViewModel,
    modifier: Modifier
) {
    val productUiState by productViewModel.productUiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Shops, Product, Services, etc") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            Button(
                onClick = { /* */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SelectCategorySection()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Top Reviews",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        when {
            productUiState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            productUiState.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: ${productUiState.error}", color = MaterialTheme.colorScheme.error)
                }
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(productUiState.products.filter {
                        it.name.contains(searchQuery, ignoreCase = true)
                    }) { product ->
                        ProductCard(product = product)
                    }
                }
            }
        }
    }
}

@Composable
fun SelectCategorySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFDFF0D8), MaterialTheme.shapes.medium)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select the Category and Give Us Your Review!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            CategoryItem(imageUrl = "https://img.freepik.com/vecteurs-libre/style-dessin-anime-vue-boutique-du-cafe_134830-697.jpg",label = "Shop")
            CategoryItem(imageUrl = "https://previews.123rf.com/images/macrovector/macrovector1808/macrovector180800221/106226655-affiche-publicitaire-r%C3%A9aliste-de-lait-et-de-produits-laitiers-naturels-avec-du-yogourt-au-beurre-au.jpg", label = "Product")
            CategoryItem(imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRc1RsQR0YTTeKdW7JXixcTBu_R9t9weiBTMw&s", label = "Service")
        }
    }
}
        @Composable
        fun CategoryItem(imageUrl: String, label: String) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { /*  */ }
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = label,
                    modifier = Modifier.size(60.dp)
                )
                Text(text = label, fontWeight = FontWeight.Bold)
            }
        }
@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFC8E6C9))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = product.pictureUrl,
                contentDescription = product.name,
                modifier = Modifier.fillMaxWidth().height(150.dp).clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = product.description, style = MaterialTheme.typography.bodyLarge)
            Text(text = "$${product.price}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
