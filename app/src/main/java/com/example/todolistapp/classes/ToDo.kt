package com.example.todolistapp.classes

data class ToDo(
    val case: String,
    val caseId: Int,
    val isActive: Boolean = true,
)