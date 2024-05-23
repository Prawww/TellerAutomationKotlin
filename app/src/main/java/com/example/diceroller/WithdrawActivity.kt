package com.example.diceroller//package com.example.diceroller
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.Intent
//import android.graphics.Bitmap
//import android.os.Bundle
//import android.view.View
//import android.view.inputmethod.InputMethodManager
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ImageButton
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.activity.ComponentActivity
//import com.example.diceroller.network.RetrofitClient
//import com.google.zxing.BarcodeFormat
//import com.google.zxing.MultiFormatWriter
//import com.google.zxing.WriterException
//import com.google.zxing.common.BitMatrix
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import java.text.SimpleDateFormat
//import java.util.Date
//
//class WithdrawActivity : ComponentActivity() {
//
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_withdraw)
//
//
//        var acc: TextView = findViewById(R.id.account)
//        var bal: TextView = findViewById(R.id.txtBalance)
//        val button = findViewById<Button>(R.id.btn)
//        var amount = findViewById<EditText>(R.id.amount)
//        var arrowBtn = findViewById<ImageButton>(R.id.arrowBtn)
//        lateinit var msg: String
//        var amt: String
//        val inputMethodManager =
//            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//
//
//
////        RetrofitClient.instance.getCustomer().enqueue(object : Callback<EntityResponse<Customer>> {
////            override fun onResponse(
////                call: Call<EntityResponse<Customer>>,
////                response: Response<EntityResponse<Customer>>
////            ) {
////                if (response.isSuccessful) {
////                    val entityResponse = response.body()
////                    println("Message: ${entityResponse?.message}")
////                    println("Status Code: ${entityResponse?.statusCode}")
////
////                    var customer = entityResponse?.entity
////                    var accounts = customer?.customerAccount
////                    if (accounts != null) {
////                        for (account in accounts) {
////                            acc.text = account.accno.toString()
////                            bal.text = account.balance.toString()
////                        }
////
////
////
////
////                        }
////                    }
////                    val users = entityResponse?.entity
////                    // Display the users in your UI
////                } else {
////                    // Handle the error
////                }
////            }
////
////            override fun onFailure(call: Call<EntityResponse<Customer>>, t: Throwable) {
////                // Handle the failure
////            }
////        })
//    button.setOnClickListener {
//        amt = amount.text.toString()
//        var combi = "$amt"
//        var qrCodeBitmap = generateQRCode(combi, 512)
//
//        // Display QR code in ImageView
//        val imageViewQRCode = findViewById<ImageView>(R.id.imageViewQRCode)
//        imageViewQRCode.setImageBitmap(qrCodeBitmap)
//
//        amount.visibility = View.GONE
//        inputMethodManager.hideSoftInputFromWindow(amount.windowToken, 0)}
//
//
//        arrowBtn.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//
//
//       val specificTime = Date()
//           val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
//        val dateString = dateFormat.format(specificTime)
//
//
//        var wdr = Withdraw(0, "t7", 100.00, dateString,false, 1)
//        RetrofitClient.instance.withdrawReq(wdr).enqueue(object : Callback<Void> {
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                if (response.isSuccessful) {
//
//                    println("data is sent directly")
//                } else {
//                    println("data encountered error")
//                }
//            }
//
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//                // Handle the failure
//            }
//        })
//
//
//    }
//    }
//
//
//private fun generateQRCode(text: String, size: Int): Bitmap? {
//    val multiFormatWriter = MultiFormatWriter()
//    try {
//        val bitMatrix: BitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, size, size)
//        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
//        for (x in 0 until size) {
//            for (y in 0 until size) {
//                bitmap.setPixel(x, y, if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt())
//            }
//        }
//        return bitmap
//    } catch (e: WriterException) {
//        e.printStackTrace()
//    }
//    return null
//}