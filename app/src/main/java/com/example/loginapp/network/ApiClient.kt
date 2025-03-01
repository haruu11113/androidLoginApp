package com.example.loginapp.network

import retrofit2.Retrofit
import com.example.loginapp.service.AuthService
import com.example.loginapp.service.AuthenticatedService
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val BASE_URL: String = "https:///api/"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authService: AuthService = retrofit.create(AuthService::class.java)
    val authenticatedService: AuthenticatedService = retrofit.create(AuthenticatedService::class.java)
}
