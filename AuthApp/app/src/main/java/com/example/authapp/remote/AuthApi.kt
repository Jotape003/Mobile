package com.example.authapp.remote

import com.example.authapp.model.TokenResponse
import com.example.authapp.model.UserCreate
import com.example.authapp.model.UserOut
import com.example.authapp.model.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthApi {
    @POST("register")
    suspend fun register(@Body user: UserCreate): Response<UserOut>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<TokenResponse>

    @GET("me")
    suspend fun me(@Header("Authorization") token: String): Response<UserOut>
}