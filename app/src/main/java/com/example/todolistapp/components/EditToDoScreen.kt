package com.example.todolistapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistapp.viewmodel.ToDoViewModel
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun EditToDoScreen(viewModel: ToDoViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 36.dp, horizontal = 16.dp),
        topBar = {}) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            
            LazyHorizontalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                rows = GridCells.Adaptive(minSize = 30.dp)
            ) {
            
            }
            
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = { Text("Меняем планы на жизнь") },
                colors = TextFieldDefaults.colors(
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Gray,
                    unfocusedContainerColor = Color.LightGray,
                    focusedContainerColor = Color.LightGray,
                ),
            )
            
            Row {
                TextButton(onClick = {}) { Text(text = "Отменить") }
                TextButton(onClick = {}) { Text(text = "Сохранить") }
            }
            
        }
    }
}