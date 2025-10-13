package com.example.todolistapp.navigation

import androidx.navigation.NavHostController

class NavigationState(
    val navHostController: NavHostController
) {
    
    fun navigateTo(route: String) {
        navHostController.navigate(route = route) {
            popUpTo(navHostController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}