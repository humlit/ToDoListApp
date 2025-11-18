package com.example.todolistapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistapp.classes.ToDo
import com.example.todolistapp.state.AddEditToDoState
import com.example.todolistapp.viewmodel.AddEditViewModel

@Composable
fun EditToDoScreen(
    todo: ToDo,
) {
    val viewModel: AddEditViewModel = viewModel()
    val screenState = viewModel.addEditState.collectAsState()
    val currentState = screenState.value
    var case by remember { mutableStateOf(TextFieldValue(todo.case)) }
    
    if (currentState is AddEditToDoState.AddEdit) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 36.dp, horizontal = 16.dp)
                .background(color = Color.White), bottomBar = {
                BottomAppBar(containerColor = Color.White) {
                    NavigationBarItem(selected = false, onClick = {
                        viewModel.backToMainScreen()
                    }, icon = { Icon(imageVector = Icons.Default.Close, contentDescription = null) }, label = { Text(text = "Отменить") })
                    
                    NavigationBarItem(selected = false, onClick = {
                        val updatedToDo = todo.copy(
                            case = case.text
                        )
                        if (updatedToDo.case.isNotEmpty()) { //                            viewModel.confirmToDoChangesOrAddNewToDo(updatedToDo)
                        }
                    }, icon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) }, label = { Text(text = "Сохранить") })
                }
            }) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(color = Color.White),
            ) {
                LazyHorizontalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    rows = GridCells.Fixed(2),
                ) { //                    items(filtersState.filterTypeList) { filter ->
                    //                        EditMenuFilterUI(filter, onClick = { viewModel.changeFilter(filter) })
                    //                    }
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
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                    ),
                )
                
            }
        }
    }
}
