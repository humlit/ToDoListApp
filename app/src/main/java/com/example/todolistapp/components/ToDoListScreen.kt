package com.example.todolistapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.classes.ToDo
import com.example.todolistapp.viewmodel.ToDoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(
    viewModel: ToDoViewModel,
    todoList: List<ToDo>,
    onSettingScreenCallBack: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(
            modifier = Modifier.width(200.dp)
        ) {
            Text(modifier = Modifier.padding(16.dp), text = "Menu", fontSize = 24.sp)
            HorizontalDivider(modifier = Modifier.padding(4.dp))
            NavigationDrawerItem(
                label = { Text(text = "Setting") },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Setting"
                    )
                },
                selected = false,
                onClick = { onSettingScreenCallBack() },
                shape = AbsoluteCutCornerShape(0f)
            )
        }
    }) {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "What's on today?") }, navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.apply { if (isClosed) open() else close() }
                    }
                }) {
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
}