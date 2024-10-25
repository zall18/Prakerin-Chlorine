package com.example.prakerin_chlorine.Response

data class LoginResponse(
    val message: String,
    val token: String,
    val user: User
)

data class User(
    val id: Int,
    val username: String,
    val role: String,
    val email: String,
    val email_verified_at: String?,
    val created_at: String,
    val updated_at: String
)

