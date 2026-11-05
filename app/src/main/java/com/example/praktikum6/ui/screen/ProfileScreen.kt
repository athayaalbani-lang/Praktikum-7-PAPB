package com.example.praktikum6.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Profile Screen", style = MaterialTheme.typography.headlineMedium)
        Text("Ini adalah halaman profil pengguna.", style = MaterialTheme.typography.bodyLarge)
        Button(onClick = onBack) {
            Text("Kembali")
        }
    }
}
