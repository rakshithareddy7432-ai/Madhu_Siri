package com.example.madhusiri

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var latestAlert = ""   // store alert

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etOtp = findViewById<EditText>(R.id.etOtp)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSendOtp = findViewById<Button>(R.id.btnSendOtp)
        val btnAction = findViewById<Button>(R.id.btnAction)

        // Show password only for Admin
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radioAdmin) {
                etPassword.visibility = View.VISIBLE
            } else {
                etPassword.visibility = View.GONE
            }
        }

        // Dummy OTP
        btnSendOtp.setOnClickListener {
            Toast.makeText(this, "OTP Sent!", Toast.LENGTH_SHORT).show()
        }

        // Main logic
        btnAction.setOnClickListener {

            val phone = etPhone.text.toString()
            val otp = etOtp.text.toString()
            val password = etPassword.text.toString()

            if (phone.isEmpty() || otp.isEmpty()) {
                Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            when (radioGroup.checkedRadioButtonId) {

                // 🌾 FARMER
                R.id.radioFarmer -> {
                    latestAlert = "⚠️ Spraying Today Nearby!"
                    Toast.makeText(this, latestAlert, Toast.LENGTH_LONG).show()
                }

                // 🐝 BEEKEEPER
                R.id.radioBeekeeper -> {
                    if (latestAlert.isNotEmpty()) {
                        Toast.makeText(this,
                            "🐝 Alert Received:\n$latestAlert\nClose hive!",
                            Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this,
                            "No alerts yet",
                            Toast.LENGTH_SHORT).show()
                    }
                }

                // 🔑 ADMIN
                R.id.radioAdmin -> {
                    if (password == "admin123") {
                        Toast.makeText(this,
                            "Admin Logged In",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,
                            "Wrong Password",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}