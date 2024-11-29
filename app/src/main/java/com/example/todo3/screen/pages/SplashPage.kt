package com.example.todo3.screen.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todo3.model.AuthState
import com.example.todo3.model.AuthViewModel
import com.example.todo3.screen.parts.Logo
import kotlinx.coroutines.delay

@Composable
fun SplashPage(navController: NavController, authViewModel: AuthViewModel) {
    // Delay and navigation logic
    LaunchedEffect(Unit) {
        // Wait for 5 seconds
        delay(5000)
        navController.navigate("splash2") {
            popUpTo("splash") { inclusive = true } // Hapus SplashPage dari stack
        }
    }

    // Splash screen layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFF03030C), Color(0xFF2D2BAF))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Logo Image
            Logo()

            // Spacer to add space between logo and text
            Spacer(modifier = Modifier.height(16.dp))

            // Circular loading indicator
            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 4.dp
            )
        }
    }
}
