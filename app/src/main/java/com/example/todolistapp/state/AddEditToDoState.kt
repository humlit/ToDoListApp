package com.example.todolistapp.state

import com.example.todolistapp.classes.ToDo

sealed class AddEditToDoState { object Initial : AddEditToDoState()
    data class AddEdit(
        val selectedToDo: ToDo,
    ) : AddEditToDoState() {
        
        fun backTo() = copy(selectedToDo = selectedToDo)
    }
}