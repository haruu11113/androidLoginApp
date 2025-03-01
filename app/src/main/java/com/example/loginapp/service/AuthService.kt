package com.example.loginapp.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String, val device_name: String = "android")
data class LoginResponse(val access_token: String, val token_type: String)

interface AuthService {
    /**
     * fun login(@Body request: LoginRequest): Call<LoginResponse>
     *
     */
    @POST("auth/login")  // APIのエンドポイント
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}