package com.example.praktikum6.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praktikum6.ui.detail.DetailUiState

@Composable
fun DetailScreen(
    uiState: DetailUiState,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Detail Student", style = MaterialTheme.typography.headlineMedium)
        when {
            uiState.isLoading -> {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.errorMessage != null ->
                Text(uiState.errorMessage ?: "Error")
            uiState.student != null -> {
                val student = uiState.student
                Text("ID: ${student.id}")
                Text("Nama: ${student.name}")
                Text("Email: ${student.email}")
                Text("University: ${student.university}")
            }
        }
        Button(onClick = onBack) {
            Text("Kembali")
        }
    }
}
