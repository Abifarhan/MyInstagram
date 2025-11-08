package com.example.myinstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.myinstagram.auth.AuthNavHost
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myinstagram.auth.AuthViewModel
import com.example.myinstagram.ui.theme.MyInstagramTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import androidx.compose.runtime.getValue
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Timber.d("MainActivity onCreate")

        setContent {
            MyInstagramTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = viewModel()

                    var isAuthenticated by remember { mutableStateOf(firebaseAuth.currentUser != null) }

                    DisposableEffect(Unit) {
                        val listener = FirebaseAuth.AuthStateListener { auth ->
                            isAuthenticated = auth.currentUser != null
                        }
                        firebaseAuth.addAuthStateListener(listener)
                        onDispose {
                            firebaseAuth.removeAuthStateListener(listener)
                        }
                    }

                    if (!isAuthenticated) {
                        AuthNavHost(
                            navController = navController,
                            viewModel = authViewModel,
                            onAuthSuccess = {
                                // Will be handled by AuthStateListener
                            }
                        )
                    } else {
                        MainContent(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun MainContent(navController: androidx.navigation.NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Welcome to Instagram Clone!")
            Button(
                onClick = {
                    FirebaseAuth.getInstance().signOut()
                }
            ) {
                Text("Sign Out")
            }
        }
    }
}
