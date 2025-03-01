package com.example.loginapp.activity

import android.os.Bundle
import android.content.Intent
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import com.example.loginapp.TokenManager
import com.example.loginapp.network.ApiClient
import com.example.loginapp.service.LoginRequest
import com.example.loginapp.service.LoginResponse
import com.example.loginapp.activity.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.util.Log

class LoginActivity : AppCompatActivity() {
    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tokenManager = TokenManager(this)

        val emailInput = findViewById<EditText>(R.id.email)
        val passwordInput = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val loginRequest = LoginRequest(email, password)
            ApiClient.authService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val token = response.body()?.access_token
                        if (token != null) {
                            tokenManager.saveToken(token)
                            Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Invalid token", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Network error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
