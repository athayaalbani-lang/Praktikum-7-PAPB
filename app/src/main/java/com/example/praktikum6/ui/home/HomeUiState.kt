package com.example.praktikum6.ui.home

import com.example.praktikum6.data.model.Student

data class HomeUiState(
    val students: List<Student> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = ""
)
