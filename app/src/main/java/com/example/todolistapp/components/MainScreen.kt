package com.example.todolistapp.components

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolistapp.classes.HomeScreenState
import com.example.todolistapp.viewmodel.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: ToDoViewModel, onSettingScreen: () -> Unit) {
    val state = viewModel.todoState.collectAsStateWithLifecycle()
    
    when (val currentState = state.value) {
        is HomeScreenState.ToDos -> {
            ToDoListScreen(
                viewModel = viewModel,
                todoList = currentState.data.toDosState.toDoList,
                onSettingScreenCallBack = { onSettingScreen() })
            Log.d("LIST_CHECK", currentState.data.toDosState.toDoList.toString())
        }
        
        is HomeScreenState.EditToDo, is HomeScreenState.AddToDo -> {
            EditToDoScreen(
                viewModel = viewModel,
                filtersState = currentState.data.filterState,
                todo = currentState.data.toDosState.selectedToDo,
            )
        }
        
        is HomeScreenState.Initial -> {
        
        }
    }
}
