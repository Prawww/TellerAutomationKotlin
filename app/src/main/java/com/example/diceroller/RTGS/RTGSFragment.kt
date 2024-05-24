package com.example.diceroller.RTGS

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.diceroller.R
import com.example.diceroller.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class RTGSFragment : Fragment(R.layout.fragment_r_t_g_s) {
    override fun onViewCreated(view: View, savedInstance: Bundle?){
        super.onViewCreated(view, savedInstance)

        val btn = view.findViewById<Button>(R.id.btn)
        val txtAccount = view.findViewById<TextView>(R.id.account)
        val txtBaln = view.findViewById<TextView>(R.id.balance)
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        val items = listOf("USD", "KES")

        GlobalScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.instance.getCustomer(1).awaitResponse()
            if (response.isSuccessful) {
                println("data sent directly")
                Log.d(requireContext().toString(), "thread is ${Thread.currentThread().name}")

                val res = response.body()
                val entity = res?.entity
                val customers = entity?.customerAccount
                for(customer in customers!!){
                    withContext(Dispatchers.Main) {
                    txtAccount.text = customer.accno.toString()
                    txtBaln.text = customer.balance.toString()
                }}

            }
            else{
                println("data encountered an error.")
            }}

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                val selectedItem = parent.getItemAtPosition(position).toString()
//                Toast.makeText(requireContext(), "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//                // Handle the case when no item is selected
//            }
//        }


        btn.setOnClickListener(){
            val secondFragment = RTGSpg2Fragment()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, secondFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()
        }
    }
}