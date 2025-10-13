package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.components.MainScreen
import com.example.todolistapp.components.SettingScreen
import com.example.todolistapp.navigation.AppNavGraph
import com.example.todolistapp.navigation.NavigationState
import com.example.todolistapp.navigation.Screen
import com.example.todolistapp.ui.theme.ToDoListAppTheme
import com.example.todolistapp.viewmodel.ToDoViewModel

class MainActivity : ComponentActivity() {
    
    private val viewModel by viewModels<ToDoViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListAppTheme {
                val navHostController = rememberNavController()
                val navigationState = NavigationState(navHostController)
                AppNavGraph(navHostController = navHostController, onMainScreen = {
                    MainScreen(
                        viewModel = viewModel,
                        onSettingScreen = { navigationState.navigateTo(route = Screen.Setting.route) })
                }, onSettingScreen = {
                    SettingScreen(
                        onArrowBackClick = {
                            navigationState.navigateTo(route = Screen.Main.route)
                        })
                })
            }
        }
    }
}
