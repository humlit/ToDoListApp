package com.example.todolistapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolistapp.classes.ToDo
import com.example.todolistapp.viewmodel.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(viewModel: ToDoViewModel, todoList: List<ToDo>) {
    
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Just do it!") }, navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Menu, contentDescription = null
                )
            }
        })
    }, floatingActionButton = {
        IconButton(
            onClick = { viewModel.addToDo() }, colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.LightGray, contentColor = Color.Black
            )
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 5.dp)
                .background(color = Color.White)
        ) {
            items(todoList, key = { todo -> todo.caseId }) { todo ->
                val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
                    confirmValueChange = { dismissValue ->
                        when (dismissValue) {
                            SwipeToDismissBoxValue.EndToStart -> {
                                viewModel.removeCase(toDo = todo)
                                true
                            }
                            
                            SwipeToDismissBoxValue.StartToEnd -> {
                                viewModel.openEditToDoScreen(editToDo = todo)
                                false
                            }
                            
                            else -> false
                        }
                    })
                
                SwipeToDismissBox(
                    state = swipeToDismissBoxState, backgroundContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent)
                        ) {
                            when (swipeToDismissBoxState.dismissDirection) {
                                SwipeToDismissBoxValue.StartToEnd -> {
                                    Icon(
                                        modifier = Modifier.align(Alignment.CenterStart),
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = null
                                    )
                                }
                                
                                SwipeToDismissBoxValue.EndToStart -> {
                                    Icon(
                                        modifier = Modifier.align(Alignment.CenterEnd),
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null
                                    )
                                }
                                
                                SwipeToDismissBoxValue.Settled -> {}
                            }
                        }
                    }) {
                    ToDoDisplayForm(todo = todo, activeChange = {
                        viewModel.activeChange(toDo = todo)
                    })
                }
            }
        }
    }
}