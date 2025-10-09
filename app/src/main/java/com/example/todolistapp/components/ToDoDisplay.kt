package com.example.todolistapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todolistapp.classes.ToDo

@Composable
fun ToDoDisplayForm(
    todo: ToDo,
    activeChange: (ToDo) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { activeChange(todo) }
            .padding(4.dp)
            .background(color = Color.Transparent),
    ) {
        HorizontalDivider()
        CustomText(case = todo.case, caseIsDone = todo.isDone)
        Text(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(end = 8.dp, bottom = 8.dp),
            text = "${todo.filter.filterName}#"
        )
    }
}

@Composable
fun CustomText(modifier: Modifier = Modifier, case: String, caseIsDone: Boolean) {
    Text(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .then(modifier),
        text = case,
        color = if (caseIsDone) Color.Gray else Color.Black,
        textDecoration = if (caseIsDone) TextDecoration.LineThrough else TextDecoration.None
    )
}