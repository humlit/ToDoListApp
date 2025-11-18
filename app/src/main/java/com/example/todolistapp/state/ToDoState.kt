package com.example.todolistapp.state

import com.example.todolistapp.classes.Filter
import com.example.todolistapp.classes.ToDo

data class ToDoState(
    val toDosState: TasksState = TasksState(),
    val filterState: FiltersState = FiltersState()
)

data class TasksState(
    val toDoList: List<ToDo> = emptyList(),
    val selectedToDo: ToDo = ToDo(case = "")
)

data class FiltersState(
    val filterTypeList: List<Filter> = emptyList()
)