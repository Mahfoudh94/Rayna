package com.example.rayna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rayna.ui.theme.RaynaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // تفعيل وضع Edge-to-Edge
        setContent {
            RaynaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainUI(modifier: Modifier = Modifier) {
    // متغير لتخزين النص المُدخل
    var textValue by remember { mutableStateOf("") }

    // قائمة العناصر القابلة للتعديل
    val itemList = remember { mutableStateListOf<String>() }

    // قائمة مفلترة حسب النص المُدخل
    var filteredList by remember { mutableStateOf<List<String>>(itemList.toList()) }

    // تحديث القائمة المفلترة عند تغيير النص أو القائمة الأصلية
    LaunchedEffect(textValue, itemList) {
        filteredList = if (textValue.isEmpty()) {
            itemList.toList() // تحويل القائمة إلى List عادية
        } else {
            itemList.filter { it.contains(textValue, ignoreCase = true) }
        }
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // حقل الإدخال للبحث أو إضافة عنصر جديد
        TextField(
            value = textValue,
            onValueChange = { textValue = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("ابحث أو أضف عنصرًا") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // زر إضافة عنصر إلى القائمة
        Button(
            onClick = {
                if (textValue.isNotEmpty()) {
                    itemList.add(textValue)
                    textValue = "" // إعادة تعيين الحقل بعد الإضافة
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("add")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // عرض القائمة المفلترة
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredList) { item ->
                ItemCard(item, onDelete = {
                    itemList.remove(item) // حذف العنصر عند النقر على زر الحذف
                })
            }
        }
    }
}

@Composable
fun ItemCard(item: String, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item)
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "حذف")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainUI() {
    RaynaTheme {
        MainUI()
    }
}
