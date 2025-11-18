package com.example.todolistapp.classes

import com.example.todolistapp.state.FiltersState
import java.util.UUID

data class ToDo(
    val case: String,
    val caseId: UUID = UUID.randomUUID(),
    val isDone: Boolean = false,
    val filter: Filter? = FiltersState().filterTypeList.firstOrNull(),
)