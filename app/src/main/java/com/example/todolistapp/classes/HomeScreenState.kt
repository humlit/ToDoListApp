package com.example.todolistapp.classes

interface EditToDoState {
    val data: ToDoState
    fun updateData(newData: ToDoState): HomeScreenState
}


sealed class HomeScreenState {
    object Initial : HomeScreenState()
    
    data class ToDos(val data: ToDoState) : HomeScreenState()
    data class EditToDo(override val data: ToDoState) : HomeScreenState(), EditToDoState {
        override fun updateData(newData: ToDoState) = copy(data = newData)
    }
    
    data class AddToDo(override val data: ToDoState) : HomeScreenState(), EditToDoState {
        override fun updateData(newData: ToDoState) = copy(data = newData)
    }
}
