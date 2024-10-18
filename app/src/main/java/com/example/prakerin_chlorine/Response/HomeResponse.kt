package com.example.prakerin_chlorine.Response

data class HomeResponse(
    val message: String,
    val data: Data
)

data class Data(
    val total_hadir: Int,
    val total_izin: Int,
    val total_alfa: Int,
    val total_tugas_selesai: Int,
    val data_user: DataUser
)

data class DataUser(
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

