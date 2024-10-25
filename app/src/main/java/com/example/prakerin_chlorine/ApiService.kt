package com.example.prakerin_chlorine

import com.example.prakerin_chlorine.Request.LoginRequest
import com.example.prakerin_chlorine.Request.PresentRequest
import com.example.prakerin_chlorine.Response.HomeResponse
import com.example.prakerin_chlorine.Response.LoginResponse
import com.example.prakerin_chlorine.Response.LogoutResponse
import com.example.prakerin_chlorine.Response.PresentResponse
import com.example.prakerin_chlorine.Response.ProfileResponse
import com.example.prakerin_chlorine.Response.SettingsResponse
import com.example.prakerin_chlorine.Response.TaskResponse
import com.example.prakerin_chlorine.Response.UserResponse
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

    @POST("absence")
    fun absence(@Header("Authorization") token: String, @Body presentRequest: PresentRequest): Call<PresentResponse>

    @GET("task")
    fun task(@Header("Authorization") token: String): Call<TaskResponse>

    @GET("setings")
    fun setting(@Header("Authorization") token: String): Call<SettingsResponse>

    @GET("profile/get")
    fun profile(@Header("Authorization") token: String): Call<ProfileResponse>

    @GET("user/get")
    fun user(@Header("Authorization") token: String): Call<UserResponse>

    @GET("auth/logout")
    fun logout(@Header("Authorization") token: String): Call<LogoutResponse>

}