package com.example.todolistapp.classes

data class ToDoState(
    val toDoList: List<ToDo> = listOf<ToDo>(
        ToDo(caseName = "Do some", case = "Need to close my computer file"),
        ToDo(caseName = "Buy new phone",case = "I chosing between s25 ultra and 17 pro max")
    )
)