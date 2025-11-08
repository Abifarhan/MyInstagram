package com.myinstagram.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    val loginState = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()
    val isLoading = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        if (!validateInput(username, password)) return

        viewModelScope.launch {
            isLoading.value = true
            try {
                firebaseAuth.signInWithEmailAndPassword(username, password).await()
                loginState.value = true
                errorMessage.value = null
            } catch (e: Exception) {
                loginState.value = false
                errorMessage.value = e.message ?: "Login failed"
            }
            isLoading.value = false
        }
    }

    fun register(username: String, password: String) {
        if (!validateInput(username, password)) return

        viewModelScope.launch {
            isLoading.value = true
            try {
                firebaseAuth.createUserWithEmailAndPassword(username, password).await()
                loginState.value = true
                errorMessage.value = null
            } catch (e: Exception) {
                loginState.value = false
                errorMessage.value = e.message ?: "Registration failed"
            }
            isLoading.value = false
        }
    }

    fun resetPassword(email: String) {
        if (email.isBlank()) {
            errorMessage.value = "Email cannot be empty"
            return
        }
        if (!email.contains("@")) {
            errorMessage.value = "Invalid email format"
            return
        }

        viewModelScope.launch {
            isLoading.value = true
            try {
                firebaseAuth.sendPasswordResetEmail(email).await()
                errorMessage.value = "Password reset link sent to $email"
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Failed to send reset email"
            }
            isLoading.value = false
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        when {
            username.isBlank() -> {
                errorMessage.value = "Username cannot be empty"
                return false
            }
            password.isBlank() -> {
                errorMessage.value = "Password cannot be empty"
                return false
            }
            password.length < 6 -> {
                errorMessage.value = "Password must be at least 6 characters"
                return false
            }
        }
        return true
    }

    fun clearError() {
        errorMessage.value = null
    }
}
