package com.example.todolistapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todolistapp.classes.ToDo

@Composable
fun ToDoDisplay(
    todo: ToDo,
    activeChange: (ToDo) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { activeChange(todo) }
            .padding(4.dp)
            .background(color = Color.LightGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
        ) {
            CustomText(todo = todo)
        }
    }
}

@Composable
fun CustomText(modifier: Modifier = Modifier, todo: ToDo) {
    Text(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .then(modifier),
        text = todo.caseName,
        color = if (todo.isActive) Color.Black else Color.Gray,
        textDecoration = if (todo.isActive) TextDecoration.None else TextDecoration.LineThrough
    )
    
    Text(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .then(modifier),
        text = todo.case,
        color = if (todo.isActive) Color.Black else Color.Gray,
        textDecoration = if (todo.isActive) TextDecoration.None else TextDecoration.LineThrough
    )
}