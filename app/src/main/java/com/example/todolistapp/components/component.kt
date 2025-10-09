package com.example.todolistapp.components

import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FilterUI(filter: String, onCLick: () -> Unit) {
    TextButton(onClick = { onCLick() }) {
        Text(text = filter)
    }
}

@Preview
@Composable
fun ModalMainScreenMenu() {
    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet {
        
        }
    }) { }
}