package com.example.prakerin_chlorine.Response

import com.example.prakerin_chlorine.Model.Task

data class TaskResponse(
    val message: String,
    val task: MutableList<Task>
)