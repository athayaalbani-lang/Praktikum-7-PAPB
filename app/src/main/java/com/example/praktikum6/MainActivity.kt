package com.example.praktikum6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.praktikum6.navigation.NavigationLabApp
import com.example.praktikum6.ui.theme.Praktikum6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Praktikum6Theme {
                NavigationLabApp()
            }
        }
    }
}
