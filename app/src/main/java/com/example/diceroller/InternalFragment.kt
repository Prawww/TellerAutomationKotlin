package com.example.diceroller

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment

class InternalFragment : Fragment(R.layout.fragment_internal) {
    override fun onViewCreated(view: View, savedInstance: Bundle?){
        super.onViewCreated(view, savedInstance)

        val btn = view.findViewById<Button>(R.id.btn)
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        val items = listOf("USD", "KES")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        btn.setOnClickListener(){
            val secondFragment = Internalpg2Fragment()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, secondFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()

        }
    }
}