package com.example.todolistapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.classes.Filter
import com.example.todolistapp.classes.ToDo
import com.example.todolistapp.viewmodel.ToDoViewModel
import kotlinx.coroutines.launch

@Preview
@Composable
fun TestUI() {
    
    //    EditMenuFilterUI(filter = Filter(filterName = "Home", filterColor = Color.Red), onCLick = {})
}

@Composable
fun EditMenuFilterUI(
    filter: Filter,
    onCLick: () -> Unit
) {
    Card(
        modifier = Modifier.padding(3.dp),
        border = BorderStroke(width = 1.dp, color = filter.filterColor),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        TextButton(onClick = { onCLick() }) {
            Text(text = filter.filterName, color = Color.Black)
        }
    }
}

@Composable
fun ColorUI(
    color: Color,
    isActive: Boolean = false,
    onClick: () -> Unit
) {
    val borderColor = if (isActive) Color.Black else Color.Transparent
    
    Card(
        modifier = Modifier
            .size(40.dp)
            .padding(5.dp)
            .clip(CircleShape)
            .border(width = 2.dp, color = borderColor, shape = CircleShape)
            .clickable(onClick = { onClick() }),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        
    }
}

@Composable
fun CustomModalNavigationDrawer(
    filterList: List<Filter>,
    todoList: List<ToDo>,
    drawerState: DrawerState,
    isShowedPopUpClick: () -> Unit,
    onSettingScreenCallBack: () -> Unit,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    var isShowedCategories by remember { mutableStateOf(false) }
    
    LaunchedEffect(drawerState.currentValue) {
        if (drawerState.currentValue == DrawerValue.Closed) {
            isShowedCategories = false
        }
    }
    
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(
            modifier = Modifier.width(200.dp)
        ) {
            Text(modifier = Modifier.padding(16.dp), text = "Menu", fontSize = 24.sp)
            
            HorizontalDivider(modifier = Modifier.padding(4.dp))
            
            NavigationDrawerItem(
                label = { Text(text = "Setting") }, icon = {
                Icon(
                    imageVector = Icons.Default.Settings, contentDescription = "Setting"
                )
            }, selected = false, onClick = {
                onSettingScreenCallBack()
                scope.launch {
                    drawerState.apply { close() }
                }
            }, shape = AbsoluteCutCornerShape(0f))
            
            NavigationDrawerItem(
                label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = "Categories")
                    Icon(
                        imageVector = if (!isShowedCategories) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                        contentDescription = null
                    )
                }
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Star, contentDescription = "Categories"
                )
            }, selected = false, onClick = {
                isShowedCategories = !isShowedCategories
            }, shape = AbsoluteCutCornerShape(0f))
            
            AnimatedVisibility(isShowedCategories) {
                Column(
                ) {
                    filterList.forEachIndexed { index, filter ->
                        NavigationDrawerItem(
                            label = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = filter.filterName)
                                
                            }
                        }, badge = {
                            Text(
                                text = "${todoList.count { it.filter.filterName == filter.filterName }}"
                            )
                        }, icon = {
                            Icon(
                                imageVector = Icons.Default.Face,
                                contentDescription = filter.filterName
                            )
                        }, selected = false, onClick = {}, shape = AbsoluteCutCornerShape(0f))
                    }
                    
                    NavigationDrawerItem(
                        label = { Text(text = "Create") },
                        selected = false,
                        onClick = {
                            isShowedPopUpClick()
                        },
                        icon = { Icon(Icons.Default.AddCircle, contentDescription = "Create") })
                }
            }
            
            NavigationDrawerItem(
                label = { Text(text = "Feedback") }, icon = {
                Icon(
                    imageVector = Icons.Default.Info, contentDescription = "Feedback"
                )
            }, selected = false, onClick = {
                
                scope.launch {
                    drawerState.apply { close() }
                }
            }, shape = AbsoluteCutCornerShape(0f))
        }
    }) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListDisplay(
    viewModel: ToDoViewModel,
    drawerState: DrawerState,
    todoList: List<ToDo>
) {
    val scope = rememberCoroutineScope()
    
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