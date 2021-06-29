package com.example.enterprises.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.enterprises.R
import com.example.enterprises.domains.UserRequest
import com.example.enterprises.extensions.Extensions.Companion.isEmptyField
import com.example.enterprises.extensions.Extensions.Companion.isValidEmail
import com.example.enterprises.utils.Utils
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private var loginEmailEditText: EditText? = null
    private var loginPasswordEditText: EditText? = null
    private var loginButton: Button? = null
    private var loginEmailTextInputLayout: TextInputLayout? = null
    private var loginPasswordTextInputLayout: TextInputLayout? = null
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewsById()
        setupLoginButton()
    }

    private fun setupLoginButton() {
        loginButton?.setOnClickListener {
            val isEmptyPasswordField: Boolean =
                loginPasswordEditText.isEmptyField(loginPasswordTextInputLayout, this)
            val isValidEmail: Boolean =
                loginEmailEditText.isValidEmail(loginEmailTextInputLayout, this)
            if (isEmptyPasswordField || !isValidEmail)
                return@setOnClickListener
            val user = UserRequest(
                loginEmailEditText?.text.toString(),
                loginPasswordEditText?.text.toString()
            )
            Utils.showProgressDialog(progressDialog, this)
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
