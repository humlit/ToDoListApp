package com.example.todolistapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todolistapp.classes.Filter
import com.example.todolistapp.classes.ToDo
import com.example.todolistapp.state.AddEditToDoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddEditViewModel() : ViewModel() {
    
    private val _addEditState = MutableStateFlow<AddEditToDoState>(AddEditToDoState.Initial)
    val addEditState: StateFlow<AddEditToDoState> = _addEditState.asStateFlow()
    
    fun addToDo() {
        _addEditState.update { currentState ->
            if (currentState !is AddEditToDoState.AddEdit) return@update currentState
            currentState.copy(
                selectedToDo = ToDo(case = "")
            )
        }
    }
    
    fun openEditToDoScreen(selectedToDo: ToDo) {
        _addEditState.update { currentState ->
            if (currentState !is AddEditToDoState.AddEdit) return@update currentState
            currentState.copy(
                selectedToDo = selectedToDo
            )
        }
    }
    
    //    fun confirmToDoChangesOrAddNewToDo(todo: ToDo) {
    //        _addEditState.update { currentState ->
    //            if (currentState !is AddEditToDoState.AddEdit) return@update currentState
    //            val findToDoIndex = currentState.todoList.toMutableList().indexOfFirst { it.caseId == todo.caseId }
    //
    //            val newToDoList = currentState.todoList.toMutableList()
    //            if (findToDoIndex != -1) {
    //                newToDoList[findToDoIndex] = todo
    //            } else {
    //                newToDoList.add(todo)
    //            }
    //
    //            AddEditToDoState.AddEdit(
    //                todoList = newToDoList, selectedToDo = ToDo(case = ""), filterTypeList =
    //            )
    //        }
    //    }
    
    fun changeFilter(filter: Filter) {
        _addEditState.update { currentState ->
            if (currentState !is AddEditToDoState.AddEdit) return@update currentState
            val updatedToDo = currentState.selectedToDo.copy(filter = filter)
            
            currentState.copy(
                selectedToDo = updatedToDo
            )
        }
    }
    
    fun backToMainScreen() {
        _addEditState.update { currentState ->
            val editable = currentState as? AddEditToDoState.AddEdit ?: return@update currentState
            
            editable.backTo()
        }
    }
}