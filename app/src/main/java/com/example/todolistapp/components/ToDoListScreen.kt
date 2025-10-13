package com.example.todolistapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.todolistapp.classes.Filter
import com.example.todolistapp.classes.ToDo
import com.example.todolistapp.viewmodel.ToDoViewModel
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(
    viewModel: ToDoViewModel,
    todoList: List<ToDo>,
    filterList: List<Filter>,
    onSettingScreenCallBack: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var isShowedPopUp by remember { mutableStateOf(false) }
    var newFilterName by remember { mutableStateOf("") }
    val colorListForFilter by remember {
        mutableStateOf(
            listOf(
                Color.White, Color.Red, Color.Green, Color.Blue
            )
        )
    }
    var colorForFilter by remember { mutableStateOf(Color.White) }
    
    if (isShowedPopUp) {
        Popup(
            alignment = Alignment.BottomCenter,
            onDismissRequest = { isShowedPopUp = false },
            properties = PopupProperties(
                focusable = true, dismissOnClickOutside = true
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 150.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = newFilterName,
                        onValueChange = {
                            if (it.length <= 20) {
                                newFilterName = it
                            }
                        },
                        label = { Text(text = "Filter name") },
                        singleLine = true,
                        leadingIcon = {
                            IconButton(onClick = {
                                if (newFilterName.isNotEmpty()) {
                                    newFilterName = ""
                                }
                            }) {
                                Icon(Icons.Default.Refresh, contentDescription = null)
                            }
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                if (newFilterName.isNotEmpty()) {
                                    viewModel.addNewFilter(
                                        filterName = newFilterName, filterColor = colorForFilter
                                    )
                                    isShowedPopUp = false
                                    newFilterName = ""
                                    colorForFilter = Color.White
                                }
                            }) {
                                Icon(Icons.Default.Check, contentDescription = null)
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                        )
                    )
                }
                HorizontalDivider(color = Color.Black)
                
                LazyHorizontalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((ceil((colorListForFilter.size / 5.toDouble())).toInt() * 40).dp)
                        .background(color = Color.White), rows = GridCells.Fixed(
                        ceil((colorListForFilter.size / 5.toDouble())).toInt()
                    )
                ) {
                    items(colorListForFilter) { color ->
                        ColorUI(
                            color = color,
                            isActive = color.value == colorForFilter.value,
                            onClick = {
                                colorForFilter = color
                            })
                    }
                }
            }
        }
    }
    
    CustomModalNavigationDrawer(
        filterList = filterList,
        todoList = todoList,
        drawerState = drawerState,
        isShowedPopUpClick = { isShowedPopUp = true },
        onSettingScreenCallBack = { onSettingScreenCallBack() }) {
        ToDoListDisplay(viewModel = viewModel, drawerState = drawerState, todoList = todoList)
    }
}