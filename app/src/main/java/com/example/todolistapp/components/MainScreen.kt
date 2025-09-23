package com.example.todolistapp.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolistapp.viewmodel.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: ToDoViewModel) {
    val state = viewModel.todoState.collectAsState().value
    
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Just do it!") }, navigationIcon = {
                Icon(Icons.Default.Menu, null)
            })
        }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 5.dp)
                .background(color = Color.White)
                .border(width = 3.dp, color = Color.Black)
        ) {
            items(state.toDoList) { todo ->
                ToDoDisplay(todo = todo, activeChange = {
                    viewModel.activeChange(todo.caseId)
                })
            }
            Log.d("LIST_CHECK", state.toDoList.toString())
        }
    }
}