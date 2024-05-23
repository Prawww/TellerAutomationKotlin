package com.example.diceroller

data class EntityResponse<T>(
    val entity: T?,
    val message: String,
    val statusCode: Int)