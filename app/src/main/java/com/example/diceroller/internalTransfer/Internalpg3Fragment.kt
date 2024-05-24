package com.example.diceroller.internalTransfer

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.diceroller.HomeFragment
import com.example.diceroller.R

class Internalpg3Fragment : Fragment(R.layout.fragment_internalpg3){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnOk = view.findViewById<Button>(R.id.btnOk)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)


        btnOk.setOnClickListener(){
            Toast.makeText(requireContext(), "RTGS transaction successful", Toast.LENGTH_SHORT).show()

            val secondFragment = HomeFragment()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, secondFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()
        }

        btnCancel.setOnClickListener(){
            val secondFragment = HomeFragment()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, secondFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()



        }
    }

}