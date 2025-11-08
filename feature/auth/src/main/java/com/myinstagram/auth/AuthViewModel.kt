package com.myinstagram.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: StateFlow<Boolean?> = _loginState

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun login(username: String, password: String) {
        if (!validateInput(username, password)) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                firebaseAuth.signInWithEmailAndPassword(username, password).await()
                _loginState.value = true
                _errorMessage.value = null
            } catch (e: Exception) {
                _loginState.value = false
                _errorMessage.value = e.message ?: "Login failed"
            }
            _isLoading.value = false
        }
    }

    fun register(username: String, password: String) {
        if (!validateInput(username, password)) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                firebaseAuth.createUserWithEmailAndPassword(username, password).await()
                _loginState.value = true
                _errorMessage.value = null
            } catch (e: Exception) {
                _loginState.value = false
                _errorMessage.value = e.message ?: "Registration failed"
            }
            _isLoading.value = false
        }
    }

    fun resetPassword(email: String) {
        if (email.isBlank()) {
            _errorMessage.value = "Email cannot be empty"
            return
        }
        if (!email.contains("@")) {
            _errorMessage.value = "Invalid email format"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                firebaseAuth.sendPasswordResetEmail(email).await()
                _errorMessage.value = "Password reset link sent to $email"
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to send reset email"
            }
            _isLoading.value = false
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        when {
            username.isBlank() -> {
                _errorMessage.value = "Username cannot be empty"
                return false
            }
            password.isBlank() -> {
                _errorMessage.value = "Password cannot be empty"
                return false
            }
            password.length < 6 -> {
                _errorMessage.value = "Password must be at least 6 characters"
                return false
            }
        }
        return true
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
