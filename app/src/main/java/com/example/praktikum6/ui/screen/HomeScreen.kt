package com.example.praktikum6.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praktikum6.ui.home.HomeUiState

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onStudentClick: (Int) -> Unit,
    onRetry: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onOpenProfile: () -> Unit,
    onOpenAbout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Home", style = MaterialTheme.typography.headlineMedium)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = { Text("Cari nama mahasiswa") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            Button(onClick = onSearchClick) { Text("Cari") }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = onOpenProfile) { Text("Buka Profile") }
            OutlinedButton(onClick = onOpenAbout) { Text("Buka About") }
        }

        Text("Daftar Mahasiswa", style = MaterialTheme.typography.titleMedium)

        when {
            uiState.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.errorMessage != null -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(uiState.errorMessage ?: "Terjadi kesalahan")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = onRetry) { Text("Coba Lagi") }
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.students) { student ->
                        OutlinedButton(
                            onClick = { onStudentClick(student.id) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("${student.id} - ${student.name}")
                        }
                    }
                }
            }
        }
    }
}
