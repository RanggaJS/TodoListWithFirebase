package com.example.todo3.screen.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo3.R
import com.example.todo3.model.AuthState
import com.example.todo3.model.AuthViewModel
import com.example.todo3.screen.parts.AlternativeLogin
import com.example.todo3.screen.parts.LoginForm
import com.example.todo3.screen.parts.Logo
import com.example.todo3.screen.parts.SocialLoginButton


@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
    val vintageFont = FontFamily(
        Font(R.font.gamaamli_regular, FontWeight.Normal) // Ganti 'vintage_font' dengan nama file font Anda
    )

    // Handle auth state changes
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
            is AuthState.Error -> Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    // UI layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFF0E4CB1), Color(0xFFB868E0))
                )
            ),
        contentAlignment = Alignment.Center

    ){
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color(0xFF0E4CB1), Color(0xFFB868E0))
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Logo
            Logo()

            Spacer(modifier = Modifier.height(24.dp)) // Tambahkan spacer jika ingin jarak antar elemen
            Text(
                text = "Login",
                fontSize = 36.sp,  // Ukuran font yang lebih besar
                fontWeight = FontWeight.Bold,  // Membuat teks lebih tebal
                fontFamily = vintageFont,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp),  // Memberikan jarak ke elemen di bawahnya
                style = TextStyle(

                )
            )



            // LoginForm, pastikan tidak memiliki padding yang berlebihan di dalam komponennya
            LoginForm(
                onLoginClick = { identifier, password ->
                    authViewModel.login(identifier, password)
                },
                onSignupClick = {
                    navController.navigate("signup")
                }
            )

        }
    }

}

