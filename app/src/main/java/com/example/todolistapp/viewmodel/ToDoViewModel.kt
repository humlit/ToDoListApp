package com.example.todolistapp.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.todolistapp.classes.Filter
import com.example.todolistapp.classes.ToDo
import com.example.todolistapp.state.ToDoScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ToDoViewModel() : ViewModel() {
    
    private val sourceListToDo = mutableListOf<ToDo>().apply {
        repeat(50) {
            add(ToDo(case = "${it + 1}"))
        }
    }
    
    private val sourcedFilterList = mutableListOf<Filter>(
        Filter(
            filterName = "Word", filterColor = Color.Red
        )
    )
    private val initialState = ToDoScreenState.ToDoScreen(
        todoList = sourceListToDo, filterList = sourcedFilterList
    )
    
    private val _todoState = MutableStateFlow<ToDoScreenState>(initialState)
    val todoState: StateFlow<ToDoScreenState> = _todoState.asStateFlow()
    
    fun activeChange(toDo: ToDo) {
        _todoState.update { currentState ->
            if (currentState !is ToDoScreenState.ToDoScreen) return@update currentState
            
            val updatedTodoList = currentState.todoList.map { case ->
                if (case.caseId == toDo.caseId) {
                    case.copy(
                        isDone = !case.isDone
                    )
                } else {
                    case
                }
            }
            
            currentState.copy(
                todoList = updatedTodoList
            )
        }
    }
    
    fun addNewFilter(
        filterName: String,
        filterColor: Color
    ) {
        _todoState.update { currentState ->
            if (currentState !is ToDoScreenState.ToDoScreen) return@update currentState
            
            val newFilter = Filter(filterName = filterName, filterColor = filterColor)
            val newFilterList = currentState.filterList.toMutableList().apply {
                add(newFilter)
            }
            
            currentState.copy(
                filterList = newFilterList
            )
        }
    }
    
    fun removeCase(toDo: ToDo) {
        _todoState.update { currentState ->
            if (currentState !is ToDoScreenState.ToDoScreen) return@update currentState
            
            val newToDoList = currentState.todoList.toMutableList().apply {
                removeAll { it.caseId == toDo.caseId }
            }
            
            currentState.copy(
                todoList = newToDoList
            )
        }
    }
}
