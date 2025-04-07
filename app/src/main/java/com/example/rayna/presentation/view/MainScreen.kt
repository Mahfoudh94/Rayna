package com.example.rayna.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rayna.data.model.Product
import com.example.rayna.presentation.viewmodel.LocationViewModel
import com.example.rayna.presentation.viewmodel.ProductViewModel
import com.rayna.data.model.Location

@Composable
fun MainScreen(
    productViewModel: ProductViewModel,
    locationViewModel: LocationViewModel
) {
    val productUiState by productViewModel.productUiState.collectAsState()
    val locationUiState by locationViewModel.locationUiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(20.dp))
                .height(190.dp)
                .background(Color(0xFF1836F1)),
        ){
            Text("Select The Category and Give Us Your Review", fontSize = 10.sp, modifier = Modifier.padding(12.dp), color = Color.White)
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(locationUiState.locations) {  locatio->
                    CategoryItem(location = locatio )
                }
            }
        }
        TopReviews(productUiState.products)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, end = 20.dp, start = 20.dp),
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
                        "Search nearly Volunteer ...",
                        fontSize = 14.sp,
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

        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu Icon",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Black)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Settings Icon",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Black)
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
        Text("Top Reviews", modifier = Modifier.padding(12.dp), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(5.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize().padding(bottom = 75.dp)) {
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
