package com.example.todolistapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.classes.Filter
import com.example.todolistapp.classes.ToDo

@Preview
@Composable
fun TestUI() {

}

@Composable
fun EditMenuFilterUI(
    filter: Filter,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.padding(3.dp),
        border = BorderStroke(width = 1.dp, color = filter.filterColor),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        TextButton(onClick = { onClick() }) {
            Text(text = filter.filterName, color = Color.Black)
        }
    }
}

@Composable
fun ColorUI(
    color: Color,
    isActive: Boolean = false,
    onClick: () -> Unit
) {
    val borderColor = if (isActive) Color.Black else Color.Transparent
    
    Card(
        modifier = Modifier
            .size(40.dp)
            .padding(5.dp)
            .clip(CircleShape)
            .border(width = 2.dp, color = borderColor, shape = CircleShape)
            .clickable(onClick = { onClick() }),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        
    }
}

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
        CustomText(case = todo.case, caseIsDone = todo.isDone)
        Text(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(end = 8.dp, bottom = 8.dp),
            text = "${todo.filter?.filterName}#",
        )
        HorizontalDivider(color = if (todo.isDone) Color.Green else Color.Red)
    }
}

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    case: String,
    caseIsDone: Boolean
) {
    Text(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .then(modifier),
        text = case,
        color = if (caseIsDone) Color.Gray else Color.Black,
        textDecoration = if (caseIsDone) TextDecoration.LineThrough else TextDecoration.None
    )
}

@Composable
fun SettingOptionUI(
    modifier: Modifier = Modifier,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    TextButton(modifier = Modifier
        .fillMaxWidth()
        .then(modifier), onClick = { onButtonClick() }) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = buttonText,
            color = Color.Black,
            fontSize = 18.sp,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomExposedDropDownMenu(
    itemText: String,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    onClick: () -> Unit,
) {
    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { onExpandedChange(it) }) {
        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { onDismissRequest() }) {
            DropdownMenuItem(text = { Text(text = itemText) }, onClick = { onClick() })
        }
    }
}