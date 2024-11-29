package com.example.todo3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todo3.model.AuthViewModel
import com.example.todo3.screen.parts.LoginForm
import com.example.todo3.screen.parts.Logo
import com.example.todo3.ui.theme.Todo3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel : AuthViewModel by viewModels()
        setContent {
            Todo3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Using a Box to add gradient background and center the Logo
                    AppNavigation(modifier=Modifier.padding(innerPadding), authViewModel= authViewModel)

                }
            }
        }
    }
}




