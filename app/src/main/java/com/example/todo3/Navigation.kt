package com.example.todo3

import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo3.model.AuthViewModel
import com.example.todo3.model.Task
import com.example.todo3.screen.pages.HomePage
import com.example.todo3.screen.pages.LoginPage
import com.example.todo3.screen.pages.ProfilePage
import com.example.todo3.screen.pages.SignupPage
import com.example.todo3.screen.pages.SplashPage
import com.example.todo3.screen.pages.SplashPage2
import com.example.todo3.screen.parts.TaskItem


@Composable
fun AppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash", builder = {
        // MyNavigation.kt
//
        composable("splash") {
            SplashPage(navController = navController, authViewModel)
        }

        composable("splash2") {
            SplashPage2(navController = navController, authViewModel)
        }
//
        composable("login") {
            LoginPage(modifier, navController, authViewModel)
        }
        composable("signup") {
            SignupPage(modifier, navController, authViewModel)
        }
        composable("home") {
            HomePage(modifier, navController, authViewModel)
        }
        composable("finish") {
            ProfilePage(modifier, navController, authViewModel)

        }



    })
}