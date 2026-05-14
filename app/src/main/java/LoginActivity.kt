package com.example.madhusiri

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // ✅ DEMO OTP
    private val generatedOtp = "1234"

    // ✅ BEEKEEPER ADMIN PASSWORD
    private val beekeeperPassword = "beekeeper123"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        // ✅ GET USER ROLE
        val role =
            intent.getStringExtra("role")
                ?: "farmer"

        // ✅ INPUT FIELDS
        val etPhone =
            findViewById<EditText>(R.id.etPhone)

        val etPassword =
            findViewById<EditText>(R.id.etPassword)

        // ✅ BUTTONS
        val btnSendOtp =
            findViewById<Button>(R.id.btnSendOtp)

        val btnVerify =
            findViewById<Button>(R.id.btnVerify)

        // ✅ OTP BOXES
        val otp1 =
            findViewById<EditText>(R.id.otp1)

        val otp2 =
            findViewById<EditText>(R.id.otp2)

        val otp3 =
            findViewById<EditText>(R.id.otp3)

        val otp4 =
            findViewById<EditText>(R.id.otp4)

        // 🔐 SHOW PASSWORD ONLY FOR BEEKEEPER
        if (role == "beekeeper") {

            etPassword.visibility = View.VISIBLE

            etPassword.hint =
                "Enter Admin Password"

        } else {

            etPassword.visibility = View.GONE
        }

        // 📲 SEND OTP
        btnSendOtp.setOnClickListener {

            Toast.makeText(
                this,
                "OTP Sent: 1234",
                Toast.LENGTH_SHORT
            ).show()
        }

        // ✅ VERIFY LOGIN
        btnVerify.setOnClickListener {

            val phone =
                etPhone.text.toString().trim()

            val otp =
                otp1.text.toString() +
                        otp2.text.toString() +
                        otp3.text.toString() +
                        otp4.text.toString()

            val password =
                etPassword.text.toString().trim()

            // ❌ PHONE EMPTY
            if (phone.isEmpty()) {

                Toast.makeText(
                    this,
                    "Enter Phone Number",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // ❌ WRONG OTP
            if (otp != generatedOtp) {

                Toast.makeText(
                    this,
                    "Invalid OTP",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // ❌ WRONG BEEKEEPER PASSWORD
            if (role == "beekeeper" &&
                password != beekeeperPassword
            ) {

                Toast.makeText(
                    this,
                    "Wrong Admin Password ❌",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // ✅ LOGIN SUCCESS
            Toast.makeText(
                this,
                "Login Success ✅",
                Toast.LENGTH_SHORT
            ).show()

            // 🚀 OPEN DASHBOARD
            val intent =
                Intent(
                    this,
                    DashboardActivity::class.java
                )

            intent.putExtra(
                "role",
                role
            )

            startActivity(intent)

            finish()
        }
    }
}