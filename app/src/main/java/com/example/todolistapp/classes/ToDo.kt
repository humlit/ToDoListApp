package com.example.todolistapp.classes

import java.util.UUID

data class ToDo(
    val caseName: String,
    val case: String,
    val caseId: UUID = UUID.randomUUID(),
    val isActive: Boolean = true,
    val filterType: String? = null
)