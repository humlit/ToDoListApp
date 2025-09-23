package com.example.todolistapp.classes

data class ToDoState(
    val toDoList: List<ToDo> = listOf<ToDo>(
        ToDo(case = "dsadsa", caseId = 1, isActive = true),
        ToDo(case = "DMskamdask mdaks mdask dma", caseId = 2, isActive = false))
)