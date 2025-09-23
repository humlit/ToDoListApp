package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolistapp.components.MainScreen
import com.example.todolistapp.ui.theme.ToDoListAppTheme
import com.example.todolistapp.viewmodel.ToDoViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ToDoViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListAppTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}
