package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.navigation.AppNavGraph
import com.example.todolistapp.navigation.NavigationState
import com.example.todolistapp.navigation.Screen
import com.example.todolistapp.screens.MainScreen
import com.example.todolistapp.screens.SettingScreen
import com.example.todolistapp.ui.theme.ToDoListAppTheme

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListAppTheme {
                val navHostController = rememberNavController()
                val navigationState = NavigationState(navHostController)
                AppNavGraph(navHostController = navHostController, onMainScreen = {
                    MainScreen(
                        
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
