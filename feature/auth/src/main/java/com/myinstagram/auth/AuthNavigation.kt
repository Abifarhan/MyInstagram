package com.myinstagram.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object Register : AuthScreen("register")
    object ResetPassword : AuthScreen("reset_password")
}

@Composable
fun AuthNavHost(navController: NavHostController, viewModel: AuthViewModel, onAuthSuccess: () -> Unit) {
    NavHost(navController = navController, startDestination = AuthScreen.Login.route) {
        composable(AuthScreen.Login.route) {
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = onAuthSuccess,
                onNavigateToRegister = { navController.navigate(AuthScreen.Register.route) },
                onNavigateToReset = { navController.navigate(AuthScreen.ResetPassword.route) }
            )
        }
        composable(AuthScreen.Register.route) {
            RegisterScreen(
                viewModel = viewModel,
                onRegisterSuccess = onAuthSuccess,
                onNavigateToLogin = { navController.popBackStack(AuthScreen.Login.route, false) }
            )
        }
        composable(AuthScreen.ResetPassword.route) {
            ResetPasswordScreen(
                viewModel = viewModel,
                onNavigateToLogin = { navController.popBackStack(AuthScreen.Login.route, false) }
            )
        }
    }
}

