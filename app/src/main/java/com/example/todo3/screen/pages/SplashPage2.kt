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
import com.example.todo3.screen.parts.Logo2
import kotlinx.coroutines.delay

@Composable
fun SplashPage2(navController: NavController, authViewModel: AuthViewModel) {
    // Observe the authentication state
    val authState by authViewModel.authState.observeAsState()

    // Delay and navigation logic
    LaunchedEffect(authState) {
        // Wait for 5 seconds
        delay(5000)

        when (authState) {
            is AuthState.Authenticated -> {
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            }
            is AuthState.Unauthenticated -> {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }
            else -> { /* Do nothing if loading or error */ }
        }
    }

    // Splash screen layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFFCAFBD9), Color(0xFF99BFFB))
                )

            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Logo Image
            Logo2()

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
