package com.example.enterprises.extensions

import android.content.Context
import android.util.Patterns
import android.widget.EditText
import com.example.enterprises.R
import com.example.enterprises.constants.Constants
import com.google.android.material.textfield.TextInputLayout

class Extensions {
    companion object {
        fun EditText?.isEmptyField(textInputLayout: TextInputLayout?, context: Context): Boolean {
            return if (this?.text.toString().isEmpty()) {
                textInputLayout?.error = context.getString(R.string.fill_the_field)
                true
            } else {
                textInputLayout?.error = Constants.EMPTY
                false
            }
        }

        fun EditText?.isValidEmail(textInputLayout: TextInputLayout?, context: Context): Boolean {
            val email: String = this?.text.toString()
            return if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                textInputLayout?.error = Constants.EMPTY
                true
            } else if (email.isEmpty()) {
                textInputLayout?.error = context.getString(R.string.fill_the_field)
                false
            } else {
                textInputLayout?.error = context.getString(R.string.incorrect_email)
                false
            }
        }
    }
}