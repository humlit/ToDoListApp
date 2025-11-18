package com.example.todolistapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.classes.Filter
import com.example.todolistapp.classes.ToDo
import kotlinx.coroutines.launch

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
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = "Categories")
                    Icon(
                        imageVector = if (!isShowedCategories) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp, contentDescription = null
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
                                horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = filter.filterName)
                                
                            }
                        }, badge = {
                            Text(
                                text = "${todoList.count { it.filter?.filterName == filter.filterName }}"
                            )
                        }, icon = {
                            Icon(
                                imageVector = Icons.Default.Face, contentDescription = filter.filterName
                            )
                        }, selected = false, onClick = {}, shape = AbsoluteCutCornerShape(0f))
                    }
                    
                    NavigationDrawerItem(label = { Text(text = "Create") }, selected = false, onClick = {
                        isShowedPopUpClick()
                    }, icon = { Icon(Icons.Default.AddCircle, contentDescription = "Create") })
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