package com.example.prakerin_chlorine.Response

data class ProfileResponse(
    val message: String,
    val data_profile: DataProfile
)

data class DataProfile(
    val id: Int,
    val user_id: Int,
    val nisn: String,
    val profile_image: String,
    val full_name: String,
    val birth_day: String,
    val address: String,
    val major: String,
    val npsn: String,
    val created_at: String,
    val updated_at: String
)