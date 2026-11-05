package com.example.praktikum6.ui.detail

import com.example.praktikum6.data.model.Student

data class DetailUiState(
    val student: Student? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
