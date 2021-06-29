package com.example.enterprises.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.enterprises.R
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private var loginEmailEditText: EditText? = null
    private var loginPasswordEditText: EditText? = null
    private var loginButton: Button? = null
    private var loginEmailTextInputLayout: TextInputEditText? = null
    private var loginPasswordTextInputLayout: TextInputEditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewsById()
        setupLoginButton()
    }

    private fun setupLoginButton() {
        loginButton?.setOnClickListener {
                var isEmptyLoginField: Boolean = loginEmailEditText.
        }
    }

    private fun findViewsById() {
        loginEmailEditText = findViewById(R.id.loginEmailEditText)
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        loginEmailTextInputLayout = findViewById(R.id.loginEmailTextInputLayout)
        loginPasswordTextInputLayout = findViewById(R.id.loginPasswordTextInputLayout)
    }
}