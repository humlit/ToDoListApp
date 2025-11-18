package com.example.todolistapp.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistapp.state.ToDoScreenState
import com.example.todolistapp.viewmodel.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onSettingScreen: () -> Unit
) {
    val viewModel: ToDoViewModel = viewModel()
    val state = viewModel.todoState.collectAsStateWithLifecycle()
    
    when (val currentState = state.value) {
        is ToDoScreenState.ToDoScreen -> {
            ToDoListScreen(
                todoList = currentState.todoList, filterList = currentState.filterList, onSettingScreenCallBack = { onSettingScreen() })
        }
        
        is ToDoScreenState.Initial -> {}
    }
}
