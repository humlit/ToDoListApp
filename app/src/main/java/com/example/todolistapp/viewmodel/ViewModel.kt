package com.example.todolistapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todolistapp.classes.ToDo
import com.example.todolistapp.classes.ToDoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ToDoViewModel(
    initialState: ToDoState = ToDoState()
) : ViewModel() {
    private val _todoState = MutableStateFlow(initialState)
    val todoState: StateFlow<ToDoState> = _todoState.asStateFlow()
    
    fun addSomeNote(toDoName: String, toDoContent: String) {
        _todoState.update { currentState ->
            val newCase = ToDo(
                caseName = toDoName, case = toDoContent
            )
            
            currentState.copy(
                toDoState = currentState.toDoState.copy(
                    toDoList = currentState.toDoState.toDoList + newCase
                )
            )
        }
    }
    
    fun activeChange(toDo: ToDo) {
        _todoState.update { currentState ->
            val updatedTasks = currentState.toDoState.toDoList.map { case ->
                if (case.caseId == toDo.caseId) {
                    case.copy(
                        isActive = !case.isActive
                    )
                } else {
                    case
                }
            }
            
            currentState.copy(
                toDoState = currentState.toDoState.copy(
                    toDoList = updatedTasks
                )
            )
        }
    }
    
    fun removeCase(toDo: ToDo) {
        _todoState.update { currentState ->
            val newToDoList = currentState.toDoState.toDoList.toMutableList().apply {
                removeAll { it.caseId == toDo.caseId }
            }
            
            currentState.copy(
                toDoState = currentState.toDoState.copy(
                    toDoList = newToDoList
                )
            )
        }
    }
    
    fun editCase(toDo: ToDo, newCaseText: String) {
        _todoState.update { currentState ->
            val newToDoList = currentState.toDoState.toDoList.map { todo ->
                when {
                    todo.caseId == toDo.caseId -> {
                        todo.copy(
                            caseName = newCaseText
                        )
                    }
                    
                    else -> todo
                }
            }
            
            currentState.copy(
                toDoState = currentState.toDoState.copy(
                    toDoList = newToDoList
                )
            )
        }
    }
}
