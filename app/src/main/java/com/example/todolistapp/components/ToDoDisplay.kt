package com.example.todolistapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.todolistapp.classes.ToDo

@Composable
fun ToDoDisplay(
    todo: ToDo,
    activeChange: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Checkbox(
            checked = !todo.isActive, onCheckedChange = {activeChange()})
        
        Text(text = todo.case)
        
        IconButton(
            onClick = {
                
            }) {
            Icon(
                imageVector = Icons.Default.Edit, contentDescription = null
            )
        }
    }
}