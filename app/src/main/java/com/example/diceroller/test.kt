package com.example.diceroller

import java.text.SimpleDateFormat
import java.util.Date

class Test {
    init {
        // Initialize the properties here
        val specificTime = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val dateStrin= dateFormat.format(specificTime)
        val qrCodeData = "nationalID: 50 ,account: 976785858, Balance: 10000, transactionID: 98747464, date: $dateStrin, Amount: 1244, depositor: Kenny, depositorID:897899, depositorNumber:0719159740"

        // Split the data string into an array of key-value pairs
        val keyValuePairs = qrCodeData.split(",")

        // Define variables to store extracted values
        var nationalId: String? = null
        var accno: String? = null
        var baln: String? = null
        var tranId: String? = null
        var dateString: String? = null
        var amt: String? = null
        var dep: String? = null
        var depId: String? = null
        var depNo: String? = null

        // Iterate through the key-value pairs and extract the values
        keyValuePairs.forEach { pair ->
            // Split each pair into key and value
            val keyValue = pair.split(":")
            val key = keyValue[0].trim()
            val value = keyValue.getOrNull(1)?.trim() // Get the value, if available

            // Map the value to the corresponding variable based on the key
            when (key) {
                "nationalID" -> nationalId = value
                "account" -> accno = value
                "Balance" -> baln = value
                "transactionID" -> tranId = value
                "date" -> dateString = value
                "Amount" -> amt = value
                "depositor" -> dep = value
                "depositorID" -> depId = value
                "depositorNumber" -> depNo = value
            }
        }

        // Print the extracted values
        println("National ID: $nationalId")
        println("Account Number: $accno")
    }
}

private fun generateRandomString(length: Int): String {
    val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}
