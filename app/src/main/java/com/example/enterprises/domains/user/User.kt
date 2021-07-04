package com.example.enterprises.domains.user

import android.content.Context
import android.widget.EditText
import com.example.enterprises.R
import com.example.enterprises.constants.Constants
import com.example.enterprises.extensions.isValidEmail
import com.google.android.material.textfield.TextInputLayout

data class User(val email: String, val password: String) {
    companion object {
        fun isValidEmail(typedEmail: String, editText: EditText?, context: Context): Boolean {
            return when {
                typedEmail.isValidEmail() -> {
                    editText?.error = Constants.EMPTY
                    true
                }
                typedEmail.isEmpty() -> {
                    editText?.error = context.getString(R.string.fill_the_field)
                    false
                }
                else -> {
                    editText?.error = context.getString(R.string.incorrect_email)
                    false
                }
            }
        }

        fun isValidPassword(textInputLayout: TextInputLayout?, context: Context): Boolean {
            val typedText = textInputLayout?.editText?.text?.toString().orEmpty()
            return if (typedText.isEmpty()) {
                textInputLayout?.error = context.getString(R.string.fill_the_field)
                true
            } else {
                textInputLayout?.error = Constants.EMPTY
                false
            }
        }
    }
}
