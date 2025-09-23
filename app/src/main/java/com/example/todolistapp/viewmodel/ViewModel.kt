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
    
    fun addSomeNote(note: String) {
        _todoState.update { currentState ->
            val newCase = ToDo(
                case = note, caseId = (currentState.toDoList.last().caseId + 1)
            )
            
            currentState.copy(
                toDoList = currentState.toDoList + newCase
            )
        }
    }
    
    fun activeChange(caseId: Int) {
        _todoState.update { currentState ->
            currentState.copy(
                toDoList = currentState.toDoList.map { case->
                    if (case.caseId == caseId) {
                        case.copy(
                        isActive = !case.isActive
                        )
                    } else {
                        case
                    }
                }
            )
        }
    }
}
