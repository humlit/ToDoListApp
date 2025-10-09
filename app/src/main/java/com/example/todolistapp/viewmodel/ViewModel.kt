package com.example.todolistapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todolistapp.classes.EditToDoState
import com.example.todolistapp.classes.HomeScreenState
import com.example.todolistapp.classes.TasksState
import com.example.todolistapp.classes.ToDo
import com.example.todolistapp.classes.ToDoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ToDoViewModel() : ViewModel() {
    val sourceListToDO = mutableListOf<ToDo>().apply {
        add(ToDo(case = "Lets do it11"))
        add(ToDo(case = "Lets do it2"))
        add(ToDo(case = "Lets do it3"))
        add(ToDo(case = "Lets do it4"))
        add(ToDo(case = "Lets do it55"))
    }
    val initialState = HomeScreenState.ToDos(data = ToDoState(toDosState = TasksState(toDoList = sourceListToDO)))
    
    private val _todoState = MutableStateFlow<HomeScreenState>(initialState)
    val todoState: StateFlow<HomeScreenState> = _todoState.asStateFlow()
    
    fun addToDo() {
        _todoState.update { currentState ->
            if (currentState !is HomeScreenState.ToDos) return@update currentState
            HomeScreenState.AddToDo(data = currentState.data)
        }
    }
    
    
    fun openEditToDoScreen(editToDo: ToDo) {
        _todoState.update { currentState ->
            if (currentState !is HomeScreenState.ToDos) return@update currentState
            val newData = currentState.data.copy(
                toDosState = currentState.data.toDosState.copy(
                    selectedToDo = editToDo
                )
            )
            HomeScreenState.EditToDo(data = newData)
        }
    }
    
    fun activeChange(toDo: ToDo) {
        _todoState.update { currentState ->
            if (currentState !is HomeScreenState.ToDos) return@update currentState
            
            val updatedTasks = currentState.data.toDosState.toDoList.map { case ->
                if (case.caseId == toDo.caseId) {
                    case.copy(
                        isDone = !case.isDone
                    )
                } else {
                    case
                }
            }
            
            currentState.copy(
                data = currentState.data.copy(
                    toDosState = currentState.data.toDosState.copy(
                        toDoList = updatedTasks
                    )
                )
            )
        }
    }
    
    fun removeCase(toDo: ToDo) {
        _todoState.update { currentState ->
            if (currentState !is HomeScreenState.ToDos) return@update currentState
            
            val newToDoList = currentState.data.toDosState.toDoList.toMutableList().apply {
                removeAll { it.caseId == toDo.caseId }
            }
            
            currentState.copy(
                data = currentState.data.copy(
                    toDosState = currentState.data.toDosState.copy(
                        toDoList = newToDoList
                    )
                )
            )
        }
    }
    
    fun changeFilter(filter: String) {
        _todoState.update { currentState ->
            val editable = currentState as? EditToDoState ?: return@update currentState
            
            val updatedToDo = editable.data.toDosState.selectedToDo.copy(filter = filter)
            val updatedData = editable.data.copy(
                toDosState = editable.data.toDosState.copy(
                    selectedToDo = updatedToDo
                )
            )
            editable.updateData(newData = updatedData)
        }
    }
    
    fun confirmToDoChangesOrAddNewToDo(todo: ToDo) {
        _todoState.update { currentState ->
            if (currentState !is HomeScreenState.EditToDo && currentState !is HomeScreenState.AddToDo) return@update currentState
            val findToDoIndex = currentState.data.toDosState.toDoList.toMutableList()
                .indexOfFirst { it.caseId == todo.caseId }
            
            Log.d("FINDINDEX", findToDoIndex.toString())
            
            val newToDoList = currentState.data.toDosState.toDoList.toMutableList()
            if (findToDoIndex != -1) {
                newToDoList[findToDoIndex] = todo
            } else {
                newToDoList.add(todo)
            }
            
            HomeScreenState.ToDos(
                data = currentState.data.copy(
                    toDosState = currentState.data.toDosState.copy(
                        toDoList = newToDoList, selectedToDo = ToDo(case = "")
                    )
                )
            )
        }
    }
    
    fun backToMainScreen() {
        _todoState.update { currentState ->
            if (currentState is EditToDoState) {
                HomeScreenState.ToDos(
                    data = currentState.data.copy(
                        toDosState = currentState.data.toDosState.copy(
                            selectedToDo = ToDo(case = "")
                        )
                    )
                )
            } else {
                currentState
            }
        }
    }
}
