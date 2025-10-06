package com.example.todolistapp.classes

sealed class HomeScreenState {
    data class ToDos(val todoList: List<ToDo>) : HomeScreenState()
    
    data class EditToDo(val editToDo: ToDo) : HomeScreenState()
}
