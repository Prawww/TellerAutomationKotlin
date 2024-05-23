package com.example.diceroller

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class RTGSpg2Fragment : Fragment(R.layout.fragment_r_t_g_spg2) {
    override fun onViewCreated(view: View, savedInstance: Bundle?){
        super.onViewCreated(view, savedInstance)
        val btn = view.findViewById<Button>(R.id.btn)

        btn.setOnClickListener(){
            val secondFragment = RTGSpg3Fragment()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, secondFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()
        }
    }
}