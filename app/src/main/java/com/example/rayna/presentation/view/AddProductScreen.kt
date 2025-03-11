package com.example.rayna.presentation.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.rayna.presentation.addproduct.AddProductEvent
import com.example.rayna.presentation.viewmodel.AddProductViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddProductScreen(
    viewModel: AddProductViewModel = hiltViewModel(),
    onProductAdded: () -> Unit = {}
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        uri?.let { viewModel.onEvent(AddProductEvent.ImageUriChanged(it.toString())) }
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is AddProductViewModel.UiEvent.ShowError -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is AddProductViewModel.UiEvent.Success -> {
                    snackbarHostState.showSnackbar(event.message)
                    onProductAdded()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                state.error?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                OutlinedTextField(
                    value = state.name,
                    onValueChange = { viewModel.onEvent(AddProductEvent.NameChanged(it)) },
                    label = { Text("اسم المنتج (عربي)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = state.category,
                    onValueChange = { viewModel.onEvent(AddProductEvent.CategoryChanged(it)) },
                    label = { Text("الفئة") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )
                OutlinedTextField(
                    value = state.description,
                    onValueChange = { viewModel.onEvent(AddProductEvent.DescriptionChanged(it)) },
                    label = { Text("الوصف") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )
                OutlinedTextField(
                    value = if (state.price == 0.0) "" else state.price.toString(),
                    onValueChange = {
                        val price = it.toDoubleOrNull() ?: 0.0
                        viewModel.onEvent(AddProductEvent.PriceChanged(price))
                    },
                    label = { Text("السعر") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )
                OutlinedTextField(
                    value = if (state.rating == 0.0) "" else state.rating.toString(),
                    onValueChange = {
                        val rating = it.toDoubleOrNull() ?: 0.0
                        viewModel.onEvent(AddProductEvent.RatingChanged(rating))
                    },
                    label = { Text("التقييم (1-5 نجوم)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )
                OutlinedTextField(
                    value = if (state.imageUri.isNotBlank()) state.imageUri else "اختر صورة من الجهاز",
                    onValueChange = { /* حقل للقراءة فقط */ },
                    label = { Text("رابط الصورة") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { imagePickerLauncher.launch("image/*") },
                    enabled = false,
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )
                selectedImageUri?.let { uri ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(top = 8.dp)
                            .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
                    ) {
                        AsyncImage(
                            model = uri,
                            contentDescription = "صورة المنتج",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Button(
                    onClick = { viewModel.onEvent(AddProductEvent.Submit) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 20.dp, end = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0F5FF6),
                    )
                ) {
                    Text("إضافة المنتج")
                }
            }
        }
    }
}
