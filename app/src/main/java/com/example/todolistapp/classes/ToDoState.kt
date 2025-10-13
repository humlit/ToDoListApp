package com.example.todolistapp.classes

import androidx.compose.ui.graphics.Color

data class ToDoState(
    val toDosState: TasksState = TasksState(),
    val filterState: FiltersState = FiltersState()
)

data class TasksState(
    val toDoList: List<ToDo> = emptyList(),
    val selectedToDo: ToDo = ToDo(case = "")
)

data class FiltersState(
    val filterTypeList: List<Filter> = listOf(
        Filter(filterName = "None", filterColor = Color.Transparent), Filter(
            filterName = "Home", filterColor = Color.Red
        ), Filter(
            filterName = "Word", filterColor = Color.Blue
        ), Filter(
            filterName = "Computer Science", filterColor = Color.Magenta
        )
    )
)