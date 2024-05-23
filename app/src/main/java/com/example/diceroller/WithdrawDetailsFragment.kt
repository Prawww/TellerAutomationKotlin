package com.example.diceroller

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Button
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
import java.text.SimpleDateFormat
import java.util.Date

@Suppress("DEPRECATION")
class WithdrawDetailsFragment : Fragment(R.layout.fragment_withdraw_details) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve data from arguments
        val amt = arguments?.getString("amount")
//        val imageBitmap = arguments?.getParcelable<Bitmap>("imageBitmap")
        val name = arguments?.getString("name")
        val nationalId = arguments?.getString("nationalId")
        val accno = arguments?.getString("account")
        val baln = arguments?.getString("balance")
        val currency = arguments?.getString("currency")

        val txtAcc = view.findViewById<TextView>(R.id.txtAccount)
        val txtAmount = view.findViewById<TextView>(R.id.txtAmt)
//        val image = view.findViewById<ImageView>(R.id.imageViewQR)
        val confirm = view.findViewById<Button>(R.id.downloadQr)
        val cancel = view.findViewById<Button>(R.id.Cancel)
        var bundle: Bundle? = null
        var txtCurrency = view.findViewById<TextView>(R.id.currency)

        // Update UI elements with the retrieved data
        txtAcc.text = accno
        txtAmount.text = amt
        txtCurrency.text = currency
        // image.setImageBitmap(imageBitmap)

        val specificTime = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val dateString = dateFormat.format(specificTime)
        val tranId: String = generateRandomString(6)

        confirm.setOnClickListener{

            var doubleAmt = amt!!.toDouble()
            var bool = false
            val base = "bitImage=="
            val tran = "Withdraw"
            var combi = "Name:$name,NationalID: $nationalId, TransactionID:$tranId, Amount: $doubleAmt, Currency: $currency,IsComplete:$bool,TellerId:1, AccountId:1,TransactionType:$tran, ,Date: $dateString"
//            $name, nationalID: $nationalId ,account: $accno, Balance: $baln, transactionID: $tranId, date: $dateString, Amount: $amt
//            , amount:$amt, date:$dateString, transaction ID:$tranId    transaction ID:$tranId, transactionType: Deposit ,date:$specificTime,amount:$amt
            var qrCodeBitmap = generateQRCode(combi, 512)
//            image.setImageBitmap(qrCodeBitmap)
            var bitImage = convertImageToBase64(qrCodeBitmap)




            var wdr: Withdraw = Withdraw(0, tranId, doubleAmt, dateString,false, bitImage, 1,1,"Withdraw",currency!!)
            bundle = Bundle().apply {
                putParcelable("imageBitmap", qrCodeBitmap) // Assuming qrCodeBitmap is your image Bitmap
            }
            val secondFragment = WdrQrCodeFragment().apply {
                arguments = bundle
            }


             // Replace the current fragment with the SecondFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, secondFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()



            RetrofitClient.instance.withdrawReq(1,wdr).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {

                        println("data is sent directly")
                    } else {
                        println("data encountered error")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Handle the failure
                }



            })


        }
    cancel.setOnClickListener(){
        val secondFragment = HomeFragment().apply {
            arguments = bundle
        }

        // Replace the current fragment with the SecondFragment
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, secondFragment)
            .addToBackStack(null) // Optional: Add to back stack for navigation history
            .commit()
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

