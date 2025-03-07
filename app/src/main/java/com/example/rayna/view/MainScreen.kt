package com.example.rayna.view

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.rayna.viewmodel.ProductViewModel
import com.example.rayna.model.Product
import com.example.rayna.viewmodel.LocationViewModel
import com.rayna.data.model.Location
import com.example.rayna.R
import java.util.UUID

@Composable
fun MainScreen(
    productViewModel: ProductViewModel,
    locationViewModel: LocationViewModel,
    modifier: Modifier = Modifier
) {
    val productUiState by productViewModel.productUiState.collectAsState()
    val locationUiState by locationViewModel.locationUiState.collectAsState()
    val currentScreen by remember { mutableStateOf("Home") }
    var showDialog by remember { mutableStateOf(false) } // To show "Add Product" screen
    var showAddProduct by remember { mutableStateOf(false) }
    val context = LocalContext.current


    if (showAddProduct) {
        AddProductScreen(
            onProductAdded = { showAddProduct = false }, // Close dialog when added
            onCancel = { showAddProduct = false },
            pictureUrl = TODO(),
            // Close dialog when canceled
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    context.startActivity(Intent(context, AddProductActivity::class.java))
                },
                modifier = Modifier
                    .padding(bottom = 80.dp, end = 5.dp), // Adjusts position
                     containerColor = Color(0xFF4CAF50)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Product")
            }
        }

    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (currentScreen) {
                "Home" -> {
                    SearchBar()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .height(190.dp)
                            .background(Color(0xFF4CAF50)),
                    ) {
                        Text(
                            "Select The Category and Give Us Your Review",
                            fontSize = 10.sp,
                            modifier = Modifier.padding(12.dp),
                            color = Color.White
                        )
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            items(locationUiState.locations) { location ->
                                CategoryItem(location = location)
                            }
                        }
                    }
                    TopReviews(productUiState.products)
                }
                "AddProduct" -> AddProductScreen(
                    onProductAdded = { showAddProduct = false }, // Close dialog when added
                    onCancel = { showAddProduct = false },
                    pictureUrl = TODO(),
                    // Close dialog when canceled
                ) // Add product screen
            }
        }
    }

    // Add Product Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Add New Product") },
            text = { Text("Enter product details here...") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, end = 10.dp, start = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            TextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        "Shops,Products,Services ... etc",
                        fontSize = 12.sp,
                        color = Color.Gray,
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(25.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_fillter),
            contentDescription = "Menu Icon",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFF4CAF50))
                .padding(8.dp),
            contentScale = ContentScale.Fit, // Ensures it scales properly
            colorFilter = ColorFilter.tint(Color.White) // Tint if needed
        )


        Spacer(modifier = Modifier.width(10.dp))

        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notification Icon",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFF4CAF50))
                .padding(8.dp)
        )
    }
}

@Composable
fun CategoryItem(location: Location) {
    Card(
        modifier = Modifier
            .padding( top = 30.dp)
            .width(80.dp)
            .height(150.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = location.pictureUrl),
                contentDescription = "Custom Icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(20.dp))    )

            Text(
                text = location.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally) // توسيط النص
            )
        }
    }
}


@Composable
fun TopReviews(products: List<Product>) {
    Column(modifier = Modifier.padding(end = 10.dp, start = 10.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Color(0xFFE5EFF1)
    )
    ) {
        Text("Top Reviews", modifier = Modifier.padding(10.dp),
            fontSize = 25.sp,
            style = MaterialTheme.typography.headlineMedium);
        Spacer(modifier = Modifier.height(5.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(bottom = 75.dp)) {
            items(products) { product ->
                ProductReviewCard(product)
                Spacer(modifier = Modifier.height(3.dp))
            }
        }
    }
}

@Composable
fun ProductReviewCard(product: Product) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        ) {
        Column(modifier = Modifier.padding(0.dp)) {
            Image(
                painter = painterResource(id = product.pictureUrl),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(5.dp)) )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(5.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ){
                Text(text = product.cat, style = MaterialTheme.typography.bodyLarge)
                Text(text = "⭐ ${product.rating}", color = Color(0xFFFFA000))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = product.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun painterResource(id: String): Painter {
    return rememberImagePainter(data = id)
}




@Composable
fun AddProductScreen(
    onProductAdded: (Product) -> Unit,
    onCancel: () -> Unit,
    pictureUrl: Int
) {
    var selectedType by remember { mutableStateOf("Product") }
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        selectedImageUri = uri
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add New $selectedType", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(10.dp))

        // Dropdown menu for selecting type
        var expanded by remember { mutableStateOf(false) }
        Box {
            OutlinedButton(onClick = { expanded = true }) {
                Text(selectedType)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                listOf("Product", "Shop", "Service").forEach { type ->
                    DropdownMenuItem(text = { Text(type) }, onClick = {
                        selectedType = type
                        expanded = false
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Form Fields
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = rating,
            onValueChange = { rating = it },
            label = { Text("Rating (0.0 - 5.0)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Image Picker
        Button(onClick = { launcher.launch("image/*") }) {
            Text("Pick an Image")
        }
        selectedImageUri?.let {
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Selected Image",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Row {
            Button(onClick = {
                if (name.isNotBlank() && category.isNotBlank() && description.isNotBlank() &&
                    price.isNotBlank() && rating.isNotBlank() && selectedImageUri != null) {

                    val newProduct = Product(
                        id = UUID.randomUUID().toString(),  // Generates a unique String ID
                        name = name,
                        cat = category,
                        description = description,
                        price = price.toDouble(),
                        rating = rating.toDouble(),
                        pictureUrl = pictureUrl

                    )
                    onProductAdded(newProduct)
                }
            }) {
                Text("Add $selectedType")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    }
}