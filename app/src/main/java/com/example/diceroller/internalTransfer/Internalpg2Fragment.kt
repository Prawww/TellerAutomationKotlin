package com.example.diceroller.internalTransfer

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.diceroller.R

class Internalpg2Fragment : Fragment(R.layout.fragment_internalpg2) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn = view.findViewById<Button>(R.id.btn)

        btn.setOnClickListener(){
            val secondFragment = Internalpg3Fragment()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, secondFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()
        }
    }

}