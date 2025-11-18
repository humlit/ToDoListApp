package com.example.todolistapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistapp.classes.Filter
import com.example.todolistapp.classes.ToDo
import com.example.todolistapp.ui.theme.CustomTextType
import com.example.todolistapp.viewmodel.AddEditViewModel
import com.example.todolistapp.viewmodel.ToDoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListDisplay(
    drawerState: DrawerState,
    todoList: List<ToDo>,
    filterList: List<Filter>
) {
    val addEditViewModel: AddEditViewModel = viewModel()
    val todoViewModel: ToDoViewModel = viewModel()
    
    val scope = rememberCoroutineScope()
    var isExpandedMenu by remember { mutableStateOf(false) }
    var selectedFilter by remember { mutableStateOf(filterList.first().filterName) }
    
    
    Scaffold(topBar = {
        TopAppBar(title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "What's on today?")
                
                ExposedDropdownMenuBox(
                    expanded = isExpandedMenu, onExpandedChange = { isExpandedMenu = it }) {
                    TextField(
                        modifier = Modifier.menuAnchor(),
                        value = selectedFilter,
                        onValueChange = { selectedFilter = it },
                        textStyle = CustomTextType.small,
                        readOnly = true,
                        singleLine = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = isExpandedMenu
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent
                        ),
                    )
                    
                    ExposedDropdownMenu(
                        expanded = isExpandedMenu, onDismissRequest = { isExpandedMenu = false }) {
                        filterList.forEach { filter ->
                            DropdownMenuItem(text = { Text(text = filter.filterName) }, onClick = {
                                selectedFilter = filter.filterName
                                isExpandedMenu = false
                            })
                        }
                    }
                }
                
            }
        }, navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.apply { open() }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu, contentDescription = null
                )
            }
        })
    }, floatingActionButton = {
        IconButton(
            onClick = { addEditViewModel.addToDo() }, colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.LightGray, contentColor = Color.Black
            )
        ) {
            Icon(
                modifier = Modifier.size(30.dp), imageVector = Icons.Default.Add, contentDescription = null
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
                                todoViewModel.removeCase(toDo = todo)
                                true
                            }
                            
                            SwipeToDismissBoxValue.StartToEnd -> {
                                addEditViewModel.openEditToDoScreen(selectedToDo = todo)
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
                                        modifier = Modifier.align(Alignment.CenterStart), imageVector = Icons.Default.Edit, contentDescription = null
                                    )
                                }
                                
                                SwipeToDismissBoxValue.EndToStart -> {
                                    Icon(
                                        modifier = Modifier.align(Alignment.CenterEnd), imageVector = Icons.Default.Delete, contentDescription = null
                                    )
                                }
                                
                                SwipeToDismissBoxValue.Settled -> {}
                            }
                        }
                    }) {
                    ToDoDisplayForm(todo = todo, activeChange = {
                        todoViewModel.activeChange(toDo = todo)
                    })
                    
                }
            }
        }
    }
}