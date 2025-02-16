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
import androidx.compose.ui.unit.dp
import com.example.rayna.ui.theme.RaynaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // نفعل عرض الشاشة الكاملة حتى تعرض الواجهة بشكل صحيح
        setContent {
            RaynaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FirstUI(modifier = Modifier.padding(innerPadding)) // نرسم الواجهة الرئيسية للتطبيق
                }
            }
        }
    }
}


/**
 * Main UI component that manages the overall layout and state.
 * @param modifier Modifier for layout adjustments.
 */
@Composable
fun FirstUI(modifier: Modifier = Modifier) {
    // TODO 1: Create state variables for text input and items list
    var textValue by remember { mutableStateOf("") } // يخزن النص لي يكتبه المستخدم
    val allItems = remember { mutableStateListOf<String>() } // يخزن العناصر المضافة
    var searchQuery by remember { mutableStateOf("") } // يخزن الكلمة لي يبحث بيها المستخدم

    // يصفي العناصر حسب كلمة البحث
    val displayedItems = if (searchQuery.isEmpty()) {
        allItems
    } else {
        allItems.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    Column(
        modifier = modifier.padding(25.dp).fillMaxSize()
    ) {
        // شريط البحث و الإدخال
        SearchInputBar(
            textValue = textValue, // TODO 2: Connect to state
            onTextValueChange = { textValue = it /* TODO 3: Update text state */ }, // يحدث النص تاع المستخدم
            onAddItem = { /* TODO 4: Add item to list */
                if (textValue.isNotBlank()) {
                    allItems.add(textValue) // يضيف عنصر للقائمة
                    textValue = "" // يمسح الحقل بعد الإضافة
                }
            },
            onSearch = { query -> searchQuery = query /* TODO 5: Implement search functionality */ } // يحدث الكلمة تاع البحث
        )

        // إذا كانت القائمة فارغة نعرض رسالة، وإلا نعرض العناصر
        if (displayedItems.isEmpty()) {
            Text("No Results", modifier = Modifier.padding(16.dp))
        } else {
            // TODO 6: Display list of items using CardsList composable
            CardsList(displayedItems, onDeleteItem = { item ->
                allItems.remove(item) // يحذف العنصر من القائمة
            })
        }
    }
}

/**
 * Composable for handling text input and search functionality.
 * @param textValue Current text field value.
 * @param onTextValueChange Callback when text is updated.
 * @param onAddItem Callback when an item is added.
 * @param onSearch Callback for performing a search.
 */
@Composable
fun SearchInputBar(
    textValue: String,
    onTextValueChange: (String) -> Unit,
    onAddItem: () -> Unit,
    onSearch: (String) -> Unit
) {
    Column {
        // حقل النصص يستعملوه في البحث و الإضافة
        TextField(
            value = textValue,
            onValueChange = {
                onTextValueChange(it) // تحديث النص
                onSearch(it) // البحث مباشرة عند الكتابة
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter Text") }
        )

        // أزرار الإضافة والبحث
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onAddItem) { /* TODO 7: Handle add button click */
                Text("Add")
            }

            Button(onClick = { /* TODO 8: Handle search button click */
                onSearch(textValue) }) {
                Text("Search")
            }
        }
    }
}

/**
 * Displays a list of items using cards.
 * @param displayedItems List of items to be shown.
 * @param onDeleteItem Callback for deleting an item.
 */
@Composable
fun CardsList(displayedItems: List<String>, onDeleteItem: (String) -> Unit) {
    // TODO 9: Implement LazyColumn to display items
    // قائمة العناصر لي تقدر تديرلها Scroll بسهولة
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        // TODO 10: Create cards for each item in the list
        // بطاقة تعرض العنصر مع زر الحذف
        items(displayedItems) { item ->
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
                    Text(text = item) // عرض نص العنصر
                    IconButton(onClick = { onDeleteItem(item) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete") // زر الحذف
                    }
                }
            }
        }
    }
}