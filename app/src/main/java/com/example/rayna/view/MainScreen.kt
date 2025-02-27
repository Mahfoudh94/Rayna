package com.example.rayna.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
        NavItem(label = "Profile", icon = Icons.Default.Person, route = "profile"),
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF006400)) {
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
        when (selectedIndex) {
            0 -> ProductListScreen(productViewModel, modifier.padding(innerPadding))
            1 -> ShopListScreen(locationViewModel, modifier.padding(innerPadding))
        }
    }
}

@Composable
fun SearchBarWithAddButton(searchText: MutableState<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            placeholder = { Text("Search...") },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = {  }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008000))) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }
}

@Composable
fun ProductListScreen(productViewModel: ProductViewModel, modifier: Modifier) {
    val productUiState by productViewModel.productUiState.collectAsState()
    val searchText = remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        SearchBarWithAddButton(searchText)
        Text(
            text = "Products Top Reviews",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
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
                val filteredProducts = productUiState.products.filter {
                    it.name.contains(searchText.value, ignoreCase = true)
                }
                LazyColumn {
                    items(filteredProducts) { product ->
                        ProductCard(product = product)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ShopListScreen(locationViewModel: LocationViewModel, modifier: Modifier) {
    val locationUiState by locationViewModel.locationUiState.collectAsState()
    val searchText = remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        SearchBarWithAddButton(searchText)
        Text(
            text = "Shops Top Reviews",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        when {
            locationUiState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            locationUiState.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: ${locationUiState.error}", color = MaterialTheme.colorScheme.error)
                }
            }
            else -> {
                val filteredLocations = locationUiState.locations.filter {
                    it.name.contains(searchText.value, ignoreCase = true)
                }
                LazyColumn {
                    items(filteredLocations) { location ->
                        LocationCard(location = location)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color(0xFF90EE90))) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = product.pictureUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = product.description, style = MaterialTheme.typography.bodyLarge)
            Text(text = "$${product.price}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
//test
@Composable
fun LocationCard(location: Location) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color(0xFF90EE90))) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = location.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = location.description, style = MaterialTheme.typography.bodyLarge)
            Text(text = location.address, style = MaterialTheme.typography.bodySmall)
        }
    }
}