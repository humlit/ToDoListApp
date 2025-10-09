package com.example.todolistapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolistapp.components.MainScreen
import com.example.todolistapp.components.SettingScreen
import com.example.todolistapp.viewmodel.ToDoViewModel

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    onMainScreen: @Composable () -> Unit,
    onSettingScreen: @Composable () -> Unit,
) {
    NavHost(navController = navHostController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            onMainScreen()
        }
        composable(route = Screen.Setting.route) {
            onSettingScreen()
        }
    }
}