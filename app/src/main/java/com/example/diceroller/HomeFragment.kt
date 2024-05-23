package com.example.diceroller

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.diceroller.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



class HomeFragment : Fragment(R.layout.fragment_home) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access XML components and set up listeners or perform actions
        val btnW: Button = view.findViewById(R.id.btnCashW)
        val btnD = view.findViewById<Button>(R.id.btnCashDep)
        val btnSwift = view.findViewById<Button>(R.id.swift)
        val btnRtgs = view.findViewById<Button>(R.id.RTGS)
        val btnBankers = view.findViewById<Button>(R.id.bankers)
        val btnInternal = view.findViewById<Button>(R.id.internal)
        var fullName: TextView = view.findViewById(R.id.fullName)
        var email = view.findViewById<TextView>(R.id.email)
        var nationalId = view.findViewById<TextView>(R.id.nationalId)
        var phone = view.findViewById<TextView>(R.id.phone)
        val localDate = view.findViewById<TextView>(R.id.date)
        var date = LocalDateTime.now()
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        localDate.text = date.format(formatter).toString()

        RetrofitClient.instance.getCustomer(1).enqueue(object : Callback<EntityResponse<Customer>> {
            override fun onResponse(call: Call<EntityResponse<Customer>>, response: Response<EntityResponse<Customer>>) {
                if (response.isSuccessful) {
                    val entityResponse = response.body()
                    println("Message: ${entityResponse?.message}")
                    println("Status Code: ${entityResponse?.statusCode}")

                    val customer = entityResponse?.entity
                    fullName.text= customer?.firstName.toString() + " " + customer?.lastName.toString()
                    email.text = customer?.email
                    nationalId.text = customer?.nationalId.toString()
                    phone.text = customer?.phoneNumber.toString()


                    // Display the users in your UI
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<EntityResponse<Customer>>, t: Throwable) {
                // Handle the failure
            }
        })

        // Set OnClickListener to handle button click
        btnW.setOnClickListener {
            // Create a new instance of the SecondFragment
            val secondFragment = WithdrawFragment()

            // Replace the current fragment with the SecondFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, secondFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()

    }

        btnD.setOnClickListener {
            // Create a new instance of the SecondFragment
            val thirdFragment = DepositFragment()

            // Replace the current fragment with the SecondFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, thirdFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()

        }

        btnSwift.setOnClickListener {
            // Create a new instance of the SecondFragment
            val thirdFragment = SwiftFragment()

            // Replace the current fragment with the SecondFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, thirdFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()

        }
        btnRtgs.setOnClickListener(){
            val thirdFragment = RTGSFragment()

            // Replace the current fragment with the SecondFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, thirdFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()
        }




        btnInternal.setOnClickListener {
            // Create a new instance of the SecondFragment
            val thirdFragment = InternalFragment()

            // Replace the current fragment with the SecondFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, thirdFragment)
                .addToBackStack(null) // Optional: Add to back stack for navigation history
                .commit()

        }

            btnBankers.setOnClickListener {
                // Create a new instance of the SecondFragment
                val thirdFragment = RTGSFragment()

                // Replace the current fragment with the SecondFragment
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.flFragment, thirdFragment)
                    .addToBackStack(null) // Optional: Add to back stack for navigation history
                    .commit()

            }
    }

}