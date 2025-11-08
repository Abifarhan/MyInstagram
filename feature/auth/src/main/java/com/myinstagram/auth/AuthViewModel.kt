package com.myinstagram.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    val loginState = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()

    fun login(username: String, password: String) {
        // Simulate authentication
        if (username == "user" && password == "password") {
            loginState.value = true
            errorMessage.value = null
        } else {
            loginState.value = false
            errorMessage.value = "Invalid credentials"
        }
    }

    fun register(username: String, password: String) {
        // Simulate registration
        loginState.value = true
        errorMessage.value = null
    }

    fun resetPassword(email: String) {
        // Simulate password reset
        errorMessage.value = "Password reset link sent to $email"
    }
}
