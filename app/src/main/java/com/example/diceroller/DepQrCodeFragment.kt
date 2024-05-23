package com.example.diceroller

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment

@Suppress("DEPRECATION")
class DepQrCodeFragment : Fragment(R.layout.fragment_dep_qr_code) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageBitmap = arguments?.getParcelable<Bitmap>("imageBitmap")
        val image = view.findViewById<ImageView>(R.id.imageViewQR)

        image.setImageBitmap(imageBitmap)
    }
}
