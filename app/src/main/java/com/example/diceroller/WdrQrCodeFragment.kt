package com.example.diceroller

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream



@Suppress("DEPRECATION")
class WdrQrCodeFragment : Fragment(R.layout.fragment_wdr_qr_code) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageBitmap = arguments?.getParcelable<Bitmap>("imageBitmap")
//        val name = arguments?.getString("name")
//        val nationalId = arguments?.getString("nationalId")
//        val accno = arguments?.getString("account")
//        val baln = arguments?.getString("balance")
        val image = view.findViewById<ImageView>(R.id.imageViewQR)
        val qrDwn = view.findViewById<Button>(R.id.qrCode)
        val end = view.findViewById<Button>(R.id.end)
        image.setImageBitmap(imageBitmap)


        qrDwn.setOnClickListener{
            end.visibility = View.VISIBLE

            val fileName = "my_qr_code.png"

            downloadQRCodeImage(context, imageBitmap, fileName) { success ->
                if (success) {
                    // QR code image downloaded successfully
                    // You can perform further actions here, such as displaying a success message
                    println("Image downloaded successfully")
                } else {
                    // Failed to download QR code image
                    // You can display an error message or take appropriate action
                }
            }

        }



    }
   }

fun downloadQRCodeImage(context: Context?, qrCodeBitmap: Bitmap?, fileName: String, onComplete: (Boolean) -> Unit) {
    try {
        // Create a directory for saving images if not exists
        val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                if (!directory.exists()) {
            directory.mkdirs()
        }

        // Create a file for the image
        val file = File(directory, fileName)

        // Save the bitmap (QR code) to the file
        val outputStream: OutputStream = FileOutputStream(file)
        qrCodeBitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush()
        outputStream.close()

        // QR code image downloaded successfully
        onComplete(true)
    } catch (e: Exception) {
        // Handle error
        e.printStackTrace()
        onComplete(false)
    }
}