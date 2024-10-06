package com.example.lab09tarea

import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET("users")
    suspend fun getAllUsers(): UserResponse

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserModel
}

