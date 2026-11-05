package com.example.praktikum6.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.praktikum6.data.repository.StudentRepository

class HomeViewModel(
    private val repository: StudentRepository
) : ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
        private set

    init { loadStudents() }

    fun loadStudents() {
        uiState = uiState.copy(isLoading = true, errorMessage = null)
        repository.getStudents { result ->
            result
                .onSuccess { data -> uiState = HomeUiState(students = data) }
                .onFailure { error ->
                    uiState = HomeUiState(
                        errorMessage = error.message ?: "Gagal mengambil data"
                    )
                }
        }
    }

    fun onSearchQueryChange(query: String) {
        uiState = uiState.copy(searchQuery = query)
    }

    fun onSearchClick() {
        if (uiState.searchQuery.isBlank()) {
            loadStudents()
            return
        }
        uiState = uiState.copy(isLoading = true, errorMessage = null)
        repository.searchStudents(uiState.searchQuery) { result ->
            result
                .onSuccess { data ->
                    uiState = uiState.copy(
                        students = data, isLoading = false, errorMessage = null
                    )
                }
                .onFailure { error ->
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Pencarian gagal"
                    )
                }
        }
    }

    companion object {
        fun factory(repository: StudentRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(repository) as T
                }
            }
    }
}
