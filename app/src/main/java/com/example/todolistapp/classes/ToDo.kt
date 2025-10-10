package com.example.todolistapp.classes

import java.util.UUID

data class ToDo(
    val case: String,
    val caseId: UUID = UUID.randomUUID(),
    val isDone: Boolean = false,
    val filter: Filter = FiltersState().filterTypeList.first(),
)