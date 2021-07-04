package com.example.enterprises.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.enterprises.R
import com.example.enterprises.api.Api
import com.example.enterprises.api.DataService
import com.example.enterprises.constants.Constants
import com.example.enterprises.domains.user.User
import com.example.enterprises.domains.user.UserRequest
import com.example.enterprises.extensions.createLoadingDialog
import com.example.enterprises.extensions.showErrorDialog
import com.google.android.material.textfield.TextInputLayout
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class LoginActivity : AppCompatActivity() {
    private var loginEmailEditText: EditText? = null
    private var loginPasswordEditText: EditText? = null
    private var loginButton: Button? = null
    private var loginEmailTextInputLayout: TextInputLayout? = null
    private var loginPasswordTextInputLayout: TextInputLayout? = null
    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadingDialog = createLoadingDialog()
        findViewsById()
        setupLoginButton()
    }

    private fun setupLoginButton() {
        loginButton?.setOnClickListener {
            val isEmptyPasswordField: Boolean = User.isValidPassword(
                textInputLayout = loginPasswordTextInputLayout, this
            )
            val isValidEmail: Boolean = User.isValidEmail(
                typedEmail = loginEmailEditText?.text?.toString().orEmpty(),
                loginEmailEditText,
                this
            )
            if (isEmptyPasswordField || !isValidEmail) return@setOnClickListener
            val userRequest = UserRequest(
                loginEmailEditText?.text?.toString().orEmpty(),
                loginPasswordEditText?.text?.toString().orEmpty()
            )
            loadingDialog?.show()
            val dataService: DataService = Api.setupRetrofit().create(DataService::class.java)
            val call: Call<ResponseBody> = dataService.recoverVerifyLogin(userRequest)
            doLogin(call)
        }
    }

    private fun doLogin(call: Call<ResponseBody>) {
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                loadingDialog?.dismiss()
                when {
                    response.isSuccessful -> {
                        onSuccessLoginResponse(response)
                    }
                    response.code() == HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        loginEmailTextInputLayout?.error = Constants.BLANK_SPACE
                        loginPasswordTextInputLayout?.error =
                            getString(R.string.login_error_email_password)
                    }
                    else -> {
                        showErrorDialog(getString(R.string.occurred_error))
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                loadingDialog?.dismiss()
                showErrorDialog(getString(R.string.error_connection_fail))
            }
        }
        )
    }

    private fun onSuccessLoginResponse(response: Response<ResponseBody>) {
        val accessToken = response.headers()[Constants.HEADER_ACCESS_TOKEN]
        val uid = response.headers()[Constants.HEADER_UID]
        val client = response.headers()[Constants.HEADER_CLIENT]
        if (accessToken.isNullOrBlank() || uid.isNullOrBlank() || client.isNullOrBlank()) {
            showErrorDialog(getString(R.string.occurred_error))
        } else {
            moveToMainActivity(accessToken, uid, client)
        }
    }

    private fun moveToMainActivity(accessToken: String?, uid: String?, client: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(Constants.HEADER_ACCESS_TOKEN, accessToken)
        intent.putExtra(Constants.HEADER_UID, uid)
        intent.putExtra(Constants.HEADER_CLIENT, client)
        startActivity(intent)
    }

    private fun findViewsById() {
        loginEmailEditText = findViewById(R.id.loginEmailEditText)
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        loginEmailTextInputLayout = findViewById(R.id.loginEmailTextInputLayout)
        loginPasswordTextInputLayout = findViewById(R.id.loginPasswordTextInputLayout)
    }
}