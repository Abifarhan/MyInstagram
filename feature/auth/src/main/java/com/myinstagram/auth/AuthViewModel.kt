package com.myinstagram.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    val loginState = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()
    val isLoading = MutableLiveData<Boolean>()

    private val mockUsers = mutableMapOf(
        "user" to "password",
        "test@test.com" to "test123"
    )

    fun login(username: String, password: String) {
        if (!validateInput(username, password)) return

        viewModelScope.launch {
            isLoading.value = true
            // Simulate network delay
            delay(1000)

            if (mockUsers[username] == password) {
                loginState.value = true
                errorMessage.value = null
            } else {
                loginState.value = false
                errorMessage.value = "Invalid credentials"
            }
            isLoading.value = false
        }
    }

    fun register(username: String, password: String) {
        if (!validateInput(username, password)) return

        viewModelScope.launch {
            isLoading.value = true
            // Simulate network delay
            delay(1000)

            if (mockUsers.containsKey(username)) {
                errorMessage.value = "Username already exists"
                loginState.value = false
            } else {
                mockUsers[username] = password
                loginState.value = true
                errorMessage.value = null
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
            // Simulate network delay
            delay(1000)

            errorMessage.value = "Password reset link sent to $email"
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
