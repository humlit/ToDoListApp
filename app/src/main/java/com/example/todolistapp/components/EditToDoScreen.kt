package com.example.todolistapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import com.example.todolistapp.classes.FiltersState
import com.example.todolistapp.classes.ToDo
import com.example.todolistapp.viewmodel.ToDoViewModel
import kotlin.math.ceil

@Composable
fun EditToDoScreen(
    viewModel: ToDoViewModel, filtersState: FiltersState, todo: ToDo,
) {
    var case by remember { mutableStateOf(TextFieldValue(todo.case)) }
    
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
                    .height((ceil((filtersState.tasksFilterType.size / 5.toDouble())).toInt() * 50).dp),
                rows = GridCells.Fixed(ceil((filtersState.tasksFilterType.size / 5.toDouble())).toInt()),
            ) {
                items(filtersState.tasksFilterType) { filter ->
                    EditMenuFilterUI(filter, onCLick = { viewModel.changeFilter(filter) })
                }
            }
            
            Spacer(modifier = Modifier.height(10.dp))
            
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = case,
                onValueChange = { case = it },
                label = { Text("Меняем планы на жизнь") },
                colors = TextFieldDefaults.colors(
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Gray,
                    unfocusedContainerColor = Color.LightGray,
                    focusedContainerColor = Color.LightGray,
                ),
            )
            
            Row {
                TextButton(onClick = {
                    viewModel.backToMainScreen()
                }) { Text(text = "Отменить") }
                
                TextButton(onClick = {
                    val updatedToDo = todo.copy(
                        case = case.text
                    )
                    if (updatedToDo.case.isNotEmpty()) {
                        viewModel.confirmToDoChangesOrAddNewToDo(updatedToDo)
                    }
                }) { Text(text = "Сохранить") }
            }
        }
    }
}