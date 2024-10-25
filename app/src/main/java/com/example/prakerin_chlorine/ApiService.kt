package com.example.prakerin_chlorine

import com.example.prakerin_chlorine.Request.LoginRequest
import com.example.prakerin_chlorine.Response.HomeResponse
import com.example.prakerin_chlorine.Response.LoginResponse
import com.example.prakerin_chlorine.Response.TaskResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("home")
    fun home(@Header("Authorization") token: String): Call<HomeResponse>

    @GET("task")
    fun task(@Header("Authorization") token: String): Call<TaskResponse>

}