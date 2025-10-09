package com.example.todolistapp.classes

data class ToDoState(
    val toDosState: TasksState = TasksState(), val filterState: FiltersState = FiltersState()
)

data class TasksState(
    val toDoList: List<ToDo> = emptyList(),
    val selectedToDo: ToDo = ToDo(case = "")
)

data class FiltersState(
    val tasksFilterType: List<String> = listOf(
        "None", "Home", "Work", "Gym", "Car", "Shop", "HomeWork",
    ),
)