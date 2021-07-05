package com.example.enterprises.domains.user

import com.example.enterprises.extensions.isValidEmail

data class User(val email: String, val password: String) {
    fun isValidEmail(): EmailStatus {
        return when {
            email.isValidEmail() -> {
                EmailStatus.VALID
            }
            email.isEmpty() -> {
                EmailStatus.EMPTY
            }
            else -> {
                EmailStatus.INVALID
            }
        }
    }

    fun isValidPassword(): Boolean {
        return password.isNotEmpty()
    }
}