package com.example.diceroller

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
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
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

@Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
class WithdrawFragment : Fragment(R.layout.fragment_withdraw) {

    private lateinit var amount: EditText
    private var amt: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        var acc: TextView = view.findViewById(R.id.account)
        var bal: TextView = view.findViewById(R.id.txtBalance)
        val button = view.findViewById<Button>(R.id.button)
         //var amount = view.findViewById<EditText>(R.id.amount)
//        var btn = view.findViewById<Button>(R.id.button)
        var arrowBtn = view.findViewById<ImageButton>(R.id.arrowBtn)
       // var amt: String

        var withdrawAmt = view.findViewById<TextView>(R.id.withdrawAmt)
        var customer: Customer? = null
        var account: CustomerAccount? = null
        var name: String? = null
        var accno:String? = null
        var nationalId: String? = null
        var baln: String? = null
        var bundle: Bundle? = null
        var selectedItem: String? = null
        var currency:String? = null
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
                            accno = account.accno.toString()
                            baln = account.balance.toString()
                            acc.text = account.accno.toString()
                            bal.text = account.balance.toString()
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

        amount = view.findViewById(R.id.amount)

        button.setOnClickListener {
            amt = amount.text.toString()
            currency = selectedItem
//            val obj = mapOf(
//                "name" to  name,
//                "nationalID" to nationalId,
//                "account" to accno,
//                "balance" to baln
//
//            )
//            var id = 1
//            var tellerid = 1
//            var bool = false
//            var combi = "ID: $id,transaction ID:$tranId,amount:$amt, date:$specificTime, isComplete: $bool, image: string==,teller:$tellerid, accountid:$id,transactionType: Withdraw"
////            1,tranId,doubleAmt,dateString, false,image, 1,1, "Withdraw"
////            , amount:$amt, date:$dateString, transaction ID:$tranId name: $name, nationalID: $nationalId, account: $accno, Balance: $baln
//            var qrCodeBitmap = generateQRCode(combi, 512)
            bundle = Bundle().apply {
                putString("name", name)
                putString("nationalId", nationalId)
                putString("account", accno)
                putString("balance", baln)
                putString("amount", amt)
//                putParcelable("imageBitmap", qrCodeBitmap) // Assuming qrCodeBitmap is your image Bitmap
            }
                val secondFragment = WithdrawDetailsFragment().apply {
                arguments = bundle
            }

                requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, secondFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()

//            }


                // Display QR code in ImageView
//            val imageViewQRCode = view.findViewById<ImageView>(R.id.imageViewQRCode)
//            imageViewQRCode.setImageBitmap(qrCodeBitmap)
//            var image = convertImageToBase64(qrCodeBitmap)
//
//            amount.visibility = View.GONE
//            inputMethodManager.hideSoftInputFromWindow(amount.windowToken, 0)
//            txtAmt.text = amt
//            withdrawAmt.visibility = View.VISIBLE
//            txtAmt.visibility = View.VISIBLE
//
//
//            button.visibility = View.GONE
//                var doubleAmt = amt!!.toDouble()
//
//            var wdr: Withdraw = Withdraw(1,tranId,doubleAmt,dateString, false,image, 1,1, "Withdraw" )
//
//            RetrofitClient.instance.withdrawReq(1,wdr).enqueue(object : Callback<Void> {
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
//            }
//
//
//            val stringAmt: String = amount.text.toString()
//        btn.setOnClickListener {
                // Create a new instance of the SecondFragment
//            val secondFragment = DownloadFragment()
//
//
//            // Replace the current fragment with the SecondFragment
//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.flFragment, secondFragment)
//                .addToBackStack(null) // Optional: Add to back stack for navigation history
//                .commit()
//            val secondFragment = WdrQrCodeFragment().apply {
//                arguments = bundle
//            }
//
//            // Replace the current fragment with the SecondFragment
//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.flFragment, secondFragment)
//                .addToBackStack(null) // Optional: Add to back stack for navigation history
//                .commit()
//

  }
}}


private fun generateQRCode(text: String, size: Int): Bitmap? {
    val multiFormatWriter = MultiFormatWriter()
    try {
        val bitMatrix: BitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, size, size)
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
        for (x in 0 until size) {
            for (y in 0 until size) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt())
            }
        }
        return bitmap
    } catch (e: WriterException) {
        e.printStackTrace()
    }
    return null
}

private fun generateRandomString(length: Int): String {
    val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}

private fun convertImageToBase64(bitmap: Bitmap?): String {
    val outputStream = ByteArrayOutputStream()
    bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream) // Use the appropriate format and quality here
    val byteArray = outputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_blank, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment BlankFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic fun newInstance(param1: String, param2: String) =
//                BlankFragment().apply {
//                    arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
//                    }
//                }
