package com.example.todolistapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistapp.classes.Filter

@Preview
@Composable
fun TestUI() {
    EditMenuFilterUI(filter = Filter(filterName = "Home", filterColor = Color.Red), onCLick = {})
}

@Composable
fun EditMenuFilterUI(filter: Filter, onCLick: () -> Unit) {
    Card(
        modifier = Modifier.padding(3.dp),
        border = BorderStroke(width = 1.dp, color = filter.filterColor),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        TextButton(onClick = { onCLick() }) {
            Text(text = filter.filterName, color = Color.Magenta)
        }
    }
    
    
}
