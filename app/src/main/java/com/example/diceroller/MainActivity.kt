package com.example.diceroller

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var fullName: TextView = findViewById(R.id.fullName)
//        var email = findViewById<TextView>(R.id.email)
//        var nationalId = findViewById<TextView>(R.id.nationalId)
//        var phone = findViewById<TextView>(R.id.phone)

        val homeFragment = HomeFragment()

        supportFragmentManager.beginTransaction().apply{
            replace(R.id.flFragment, homeFragment)
            addToBackStack(null)
            commit()
        }

//        val specificTime = Date()
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
//        val dateStrin = dateFormat.format(specificTime)
//        val bool = true // Assuming you have a boolean value
//        val base = "base64_encoded_image_data==" // Placeholder for base64 image data
//        val tran = "transaction_type" // Placeholder for transaction type
//
//        val qrCodeData = "id:0, transactionID:98747464, date:$dateStrin, isCompleted:$bool, imageData:$base, tellerId:1, accountId:1, transactionType:$tran, amount:1244, depositor:Kenny, depositorID:897899, depositorNumber:0719159740"
//
//        // Split the data string into an array of key-value pairs
//        val keyValuePairs = qrCodeData.split(",")
//
//        // Define variables to store extracted values
//        var id: Long? = null
//        var transactionID: String? = null
//        var amount: Double? = null
//        var date: String? = null
//        var isComplete: Boolean? = null
//        var imageData: String? = null
//        var tellerId: Long? = null
//        var accountId: Long? = null
//        var transactionType: String? = null
//        var depositor: String? = null
//        var depositorID: String? = null
//        var depositorNumber: String? = null
//
//        // Iterate through the key-value pairs and extract the values
//        keyValuePairs.forEach { pair ->
//            // Split each pair into key and value
//            val keyValue = pair.split(":")
//            val key = keyValue[0].trim()
//            val value = keyValue.getOrNull(1)?.trim() // Get the value, if available
//
//            // Map the value to the corresponding variable based on the key
//            when (key) {
//                "id" -> id = value?.toLong()
//                "transactionID" -> transactionID = value
//                "date" -> date = value
//                "isComplete" -> isComplete = value?.toBoolean()
//                "imageData" -> imageData = value
//                "tellerId" -> tellerId = value?.toLong()
//                "accountId" -> accountId = value?.toLong()
//                "transactionType" -> transactionType = value
//                "Amount" -> amount = value?.toDouble()
//                "depositor" -> depositor = value
//                "depositorID" -> depositorID = value
//                "depositorNumber" -> depositorNumber = value
//            }
//        }
//
//        // Now you can use the extracted values as needed
//        println("ID: $id")
//        println("Transaction ID: $transactionID")
//        println("Is Complete: $isComplete")
//        println("date: $date")
//        println("Image Data: $imageData")
//        println("Teller ID: $tellerId")
//        println("Account ID: $accountId")
//        println("Transaction Type: $transactionType")
//        println("Amount: $amount")
//        println("Depositor: $depositor")
//        println("Depositor ID: $depositorID")
//        println("Depositor Number: $depositorNumber")
//
//        var dep = Deposit(id!!,transactionID!!,amount!!,date!!, isComplete!!, imageData!!, tellerId!!,accountId!!,transactionType!!,depositor!!, depositorID!!, depositorNumber!! )

////
//
//        val withdrawFragment = WithdrawFragment()
//
//        val withdraw = findViewById<Button>(R.id.withdraw)
//        withdraw.setOnClickListener{
//            supportFragmentManager.beginTransaction().apply{
//                replace(R.id.flFragment, withdrawFragment)
//                addToBackStack(null)
//                commit()
//            }
//        }

//        val withdraw = findViewById<Button>(R.id.withdraw)
//        withdraw.setOnClickListener{
//            val intent = Intent(this, WithdrawActivity::class.java)
//            startActivity(intent)}

//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://192.168.90.175:7700/api/v1/tran/")
//            .addConverterFactory(GsonConverterFactory.create()) // Gson converter
//            .build()
//
//        val apiService = retrofit.create(ApiService::class.java)
//
//        val call = apiService.getUsers()
//
//            call.enqueue(object : Callback<EntityResponse> {
//            override fun onResponse(
//                call: Call<EntityResponse>,
//                response: Response<EntityResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val entityResponse = response.body()
//                    println("Message: ${entityResponse?.message}")
//                    println("Status Code: ${entityResponse?.statusCode}")
////                    txt.text = entityResponse?.message
//                    val users = entityResponse?.entity
//                    for( user in users.orEmpty()){
//                    println("User ID: ${user?.id}")
//                        txt.text = user?.accountId
//                    println("User Name: ${user?.accountId}")
//                    println("User Email: ${user?.trantype}")}
//                } else {
//                    println("Request failed")
//                }
//            }
//
//            override fun onFailure(call: Call<EntityResponse>, t: Throwable) {
//                println("Network error: ${t.message}")
//            }
//        })
        RetrofitClient.instance.getCustomer(1).enqueue(object : Callback<EntityResponse<Customer>> {
            override fun onResponse(call: Call<EntityResponse<Customer>>, response: Response<EntityResponse<Customer>>) {
                if (response.isSuccessful) {
                    val entityResponse = response.body()
                    println("Message: ${entityResponse?.message}")
                    println("Status Code: ${entityResponse?.statusCode}")

                    val customer = entityResponse?.entity
//                    fullName.text= customer?.firstName.toString() + customer?.lastName.toString()
//                    email.text = customer?.email
//                    nationalId.text = customer?.nationalId.toString()
//                    phone.text = customer?.phoneNumber.toString()

                    // Display the users in your UI
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<EntityResponse<Customer>>, t: Throwable) {
                // Handle the failure
            }
        })

    }}

//package com.example.diceroller
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import androidx.activity.ComponentActivity
//import androidx.fragment.app.commit
//import com.example.diceroller.network.RetrofitClient
//import com.example.diceroller.network.entity.EntityResponse
//import com.example.diceroller.network.entity.Customer
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MainActivity : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val fullName: TextView = findViewById(R.id.fullName)
//        val email: TextView = findViewById(R.id.email)
//        val nationalId: TextView = findViewById(R.id.nationalId)
//        val phone: TextView = findViewById(R.id.phone)
//
//        val withdrawFragment = WithdrawFragment()
//
//        supportFragmentManager.commit {
//            replace(R.id.flFragment, withdrawFragment)
//            addToBackStack(null)
//        }
//
//        val withdrawButton: Button = findViewById(R.id.withdraw)
//        withdrawButton.setOnClickListener {
//            supportFragmentManager.commit {
//                replace(R.id.flFragment, withdrawFragment)
//                addToBackStack(null)
//            }
//        }
//
//        RetrofitClient.instance.getCustomer().enqueue(object : Callback<EntityResponse<Customer>> {
//            override fun onResponse(call: Call<EntityResponse<Customer>>, response: Response<EntityResponse<Customer>>) {
//                if (response.isSuccessful) {
//                    val entityResponse = response.body()
//                    println("Message: ${entityResponse?.message}")
//                    println("Status Code: ${entityResponse?.statusCode}")
//
//                    val customer = entityResponse?.entity
//                    fullName.text = "${customer?.firstName} ${customer?.lastName}"
//                    email.text = customer?.email
//                    nationalId.text = customer?.nationalId.toString()
//                    phone.text = customer?.phoneNumber.toString()
//
//                    // Display the customer details in your UI
//                } else {
//                    // Handle the error
//                }
//            }
//
//            override fun onFailure(call: Call<EntityResponse<Customer>>, t: Throwable) {
//                // Handle the failure
//            }
//        })
//    }
//}

