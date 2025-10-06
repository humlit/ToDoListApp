package com.example.todolistapp.classes

data class ToDoState(
    val toDoState: TasksState = TasksState(),
    val filterState: FiltersState = FiltersState()
    )

data class TasksState(
    val toDoList: List<ToDo> = emptyList(),
)


data class FiltersState(
    val tasksFilterType: List<String> = listOf("None"),
)