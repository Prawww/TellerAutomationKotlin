package com.example.diceroller

data class CustomerAccount(
    val accno: Long,
    val balance: Double,
    val id: Long,
    val transactions: List<Transaction>
)
