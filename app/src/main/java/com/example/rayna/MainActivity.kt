package com.example.rayna
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rayna.ui.theme.RaynaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RaynaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FirstUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun FirstUI(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }
    val raynaItems = remember { mutableStateListOf<String>() }
    val isFound = remember { mutableStateOf(false) }
    val isDialogOpen = remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .padding(23.dp)
            .fillMaxSize()
    ) {
        SearchInputBar(
            textValue = searchText,
            onTextValueChange = { newText -> searchText = newText ;/*search */},
            onAddItem = {
                if (searchText.isNotEmpty()){raynaItems.add(searchText) }
                if (searchText.isEmpty()){ isDialogOpen.value = true } /*alert if input is empty */
                searchText = "" },
        )
        if (!isFound.value) Text(text = "no items found") /* no search result */
        CardsList(
            displayedItems = if (searchText.isEmpty()) raynaItems
        else {raynaItems.filter { it.contains(searchText, ignoreCase = true) }}
            , onDelete = {item -> /* delete item function */
            raynaItems.remove(item)
        }
            , isFound)
        ShowAlertDialog(isDialogOpen)

    }
}

@Composable
fun SearchInputBar(
    textValue: String,
    onTextValueChange: (String) -> Unit,
    onAddItem: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = textValue,
            shape = RoundedCornerShape(24.dp),
            onValueChange = onTextValueChange,
            modifier = Modifier.weight(1f)
                .height(50.dp),
            placeholder = { Text("Search or Enter text ...") },
            textStyle = TextStyle(textAlign = TextAlign.Start, fontSize = 16.sp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                disabledIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,

            ),
        )
        Button(
            onClick = onAddItem,
            modifier = Modifier
                .height(50.dp)
                .padding(start = 5.dp)
        ) {
            Text("Add")
        }
    }
}


@Composable
fun CardsList(displayedItems: List<String>, onDelete: (String) -> Unit , isFound: MutableState<Boolean>) {
    if (displayedItems.isEmpty()){
        isFound.value = false
    }else{
        isFound.value = true
    };
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(displayedItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = item, modifier = Modifier.weight(1f))
                    Button(onClick = { onDelete(item) } ,
                        modifier = Modifier.height(40.dp)
                        ,colors = ButtonDefaults.buttonColors(containerColor = Color.Black , contentColor = Color.White)
                    ) {
                        Text(text = "delete" ,  fontSize = 13.sp)
                    }
                }
            }
        }
    }
}
@Composable
fun ShowAlertDialog(isDialogOpen: MutableState<Boolean>) {
    if (isDialogOpen.value) {
        AlertDialog(
            onDismissRequest = { isDialogOpen.value = false },
            title = { Text("Input is empty") },
            text = { Text("Please fill the Textfield and try again .") },
            confirmButton = {
                Button(onClick = { isDialogOpen.value = false }) {
                    Text("OK")
                }
            },
        )
    }
}
