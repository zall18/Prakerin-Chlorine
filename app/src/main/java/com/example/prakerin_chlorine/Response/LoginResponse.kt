package com.example.prakerin_chlorine.Response

import com.example.prakerin_chlorine.Model.User

data class LoginResponse(
    val message: String,
    val token: String,
    val user: User
)
