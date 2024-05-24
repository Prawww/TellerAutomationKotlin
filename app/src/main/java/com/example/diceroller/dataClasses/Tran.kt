package com.example.diceroller.dataClasses

data class Tran(
    val accountId: String,
    val amount: Double,
    val bankId: Int,
    val compId: Any,
    val entryId: String,
    val id: Int,
    val solId: Int,
    val tranDate: Any,
    val tranParticular: String,
    val transactionId: String,
    val trantype: String,
    val userId: String
)