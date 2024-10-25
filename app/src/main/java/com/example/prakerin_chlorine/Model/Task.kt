package com.example.prakerin_chlorine.Model

data class Task(
    val id: Int,
    val student_id: Int,
    val name: String,
    val description: String,
    val is_done: Int,
    val created_at: String,
    val updated_at: String
)
