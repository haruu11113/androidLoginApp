package com.example.loginapp.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

data class UserProfile(val id: Int, val name: String, val email: String)

interface AuthenticatedService {
    @GET("user/profile")
    fun getProfile(@Header("Authorization") token: String): Call<UserProfile>
}