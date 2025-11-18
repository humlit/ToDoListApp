package com.example.todolistapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolistapp.components.SettingOptionUI
import com.example.todolistapp.usercase.SettingOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    onArrowBackClick: () -> Unit
) {
    val option = listOf(
        SettingOption(text = "Categories edit", onClick = {}),
    )
    
    val list = listOf("1", "2", "3")
    
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Setting") }, navigationIcon = {
                IconButton(onClick = {
                    onArrowBackClick()
                }) {
                    Icon(
                        Icons.Default.ArrowBack, contentDescription = "Back"
                    )
                }
            })
        }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(2), modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp), verticalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                
                }
            }
            
            items(option, key = { it.text }) { option ->
                SettingOptionUI(
                    buttonText = option.text, onButtonClick = { option.onClick() })
            }
        }
    }
}