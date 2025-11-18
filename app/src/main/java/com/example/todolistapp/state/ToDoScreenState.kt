package com.example.todolistapp.state

import com.example.todolistapp.classes.Filter
import com.example.todolistapp.classes.ToDo

sealed class ToDoScreenState {
    
    object Initial : ToDoScreenState()
    
    data class ToDoScreen(
        val todoList: List<ToDo>,
        val filterList: List<Filter>
    ) : ToDoScreenState()
    
}

