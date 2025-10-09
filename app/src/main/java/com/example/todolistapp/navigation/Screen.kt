package com.example.todolistapp.navigation

sealed class Screen(val route: String) {
    object Main : Screen(route = ROUTE_MAIN)
    object Setting : Screen(route = ROUTE_SETTING)
    
    private companion object {
        const val ROUTE_MAIN = "main_screen"
        const val ROUTE_SETTING = "setting"
    }
}