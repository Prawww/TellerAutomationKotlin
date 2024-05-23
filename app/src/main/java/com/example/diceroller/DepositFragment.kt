package com.example.diceroller

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.diceroller.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DepositFragment : Fragment(R.layout.fragment_deposit) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        var acc: TextView = view.findViewById(R.id.account)
        var bal: TextView = view.findViewById(R.id.txtBalance)
        val button = view.findViewById<Button>(R.id.btn)
        var amount = view.findViewById<EditText>(R.id.amount)
        var btn = view.findViewById<Button>(R.id.button)
        var arrowBtn = view.findViewById<ImageButton>(R.id.arrowBtn)
         var amt: String
        var depositor = view.findViewById<EditText>(R.id.depositor)
        var depositorNum = view.findViewById<EditText>(R.id.depositorNum)
        var depositorId = view.findViewById<EditText>(R.id.depositorId)
        var customer: Customer? = null
        var name: String? = null
        var accno:String? = null
        var nationalId: String? = null
        var baln: String? = null
        var dep: String? = null
        var depNo:String? = null
        var depId: String? = null
        var bundle: Bundle? = null
        var currency: String? = null
        var selectedItem: String? = null
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        val items = listOf("USD", "KES")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedItem = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle the case when no item is selected
            }
        }

        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        RetrofitClient.instance.getCustomer(1).enqueue(object : Callback<EntityResponse<Customer>> {
            override fun onResponse(
                call: Call<EntityResponse<Customer>>,
                response: Response<EntityResponse<Customer>>
            ) {
                if (response.isSuccessful) {
                    val entityResponse = response.body()
                    println("Message: ${entityResponse?.message}")
                    println("Status Code: ${entityResponse?.statusCode}")

                    customer = entityResponse?.entity
                    var accounts = customer?.customerAccount
                    name = customer?.firstName + " " + customer?.lastName
                    nationalId = customer?.nationalId

                    if (accounts != null) {
                        for (account in accounts) {
                            acc.text = account.accno.toString()
                            bal.text = account.balance.toString()
                            accno = account.accno.toString()
                            baln = account.balance.toString()
                        }


                    }

                } else {
                    // Handle the error
                }
            }
            override fun onFailure(call: Call<EntityResponse<Customer>>, t: Throwable) {
                // Handle the failure
            } })
//        val specificTime = Date()
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
//        val dateString = dateFormat.format(specificTime)
//        val tranId: String = generateRandomString(6)


        button.setOnClickListener {
            amt = amount.text.toString()
            dep = depositor.text.toString()
            depId = depositorId.text.toString()
            depNo = depositorNum.text.toString()
            currency = selectedItem

//            val obj = mapOf(
//                "time" to dateString,
//                "tranId" to tranId,
//                "amount" to amt,
//            )
//            var combi = "$obj, name: $name, nationalID: $nationalId, account: $accno, Balance: $baln, depositor:$dep, depositorID:$depId, depositorNumber:$depNo"
////            , amount:$amt, date:$dateString, transaction ID:$tranId    transaction ID:$tranId, transactionType: Deposit ,date:$specificTime,amount:$amt
//            var qrCodeBitmap = generateQRCode(combi, 512)

            bundle = Bundle().apply {
                putString("name", name)
                putString("nationalId", nationalId)
                putString("account",accno)
                putString("balance", baln)
                putString("amount", amt)
                putString("currency", currency)
                putString("depositor", dep)
                putString("depositorId", depId)
                putString("depositorNo", depNo)
//                putParcelable("imageBitmap", qrCodeBitmap) // Assuming qrCodeBitmap is your image Bitmap
            }

            val secondFragment = DepositDetailsFragment().apply {
                arguments = bundle
            }

            // Replace the current fragment with the SecondFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, secondFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()
            // Display QR code in ImageView
//            val imageViewQRCode = view.findViewById<ImageView>(R.id.imageViewQRCode)
//            imageViewQRCode.setImageBitmap(qrCodeBitmap)
//            var image = convertImageToBase64(qrCodeBitmap)
//
//            amount.visibility = View.GONE
//            inputMethodManager.hideSoftInputFromWindow(amount.windowToken, 0)
//            txtAmt.text = amt
//            depositAmt.visibility = View.VISIBLE
//            txtAmt.visibility = View.VISIBLE
//
//
//            button.visibility = View.GONE
//            var doubleAmt = amt!!.toDouble()
//
//            var dpt: Deposit = Deposit(0, tranId, doubleAmt, dateString,false, image, 1,1,"Deposit",
//                dep!!, depId!!, depNo!!
//            )
//
//            RetrofitClient.instance.depositReq(1,dpt).enqueue(object : Callback<Void> {
//                override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                    if (response.isSuccessful) {
//
//                        println("data is sent directly")
//                    } else {
//                        println("data encountered error")
//                    }
//                }
//
//                override fun onFailure(call: Call<Void>, t: Throwable) {
//                    // Handle the failure
//                }
//            })
        }
//
//
//            val stringAmt: String = amount.text.toString()
//        btn.setOnClickListener {
//            // Create a new instance of the SecondFragment
//            val secondFragment = DownloadFragment().apply {
//                arguments = bundle
//            }
//
//            // Replace the current fragment with the SecondFragment
//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.flFragment, secondFragment)
//                .addToBackStack(null) // Optional: Add to back stack for navigation history
//                .commit()
//
//        }

    }
}

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
//
//private fun generateRandomString(length: Int): String {
//    val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
//    return (1..length)
//        .map { charset.random() }
//        .joinToString("")
//}
//
//private fun convertImageToBase64(bitmap: Bitmap?): String {
//    val outputStream = ByteArrayOutputStream()
//    bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream) // Use the appropriate format and quality here
//    val byteArray = outputStream.toByteArray()
//    return Base64.encodeToString(byteArray, Base64.DEFAULT)
//}