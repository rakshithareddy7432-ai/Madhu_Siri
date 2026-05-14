package com.example.madhusiri

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔥 Safe layout loading
        try {
            setContentView(R.layout.activity_user_type)
        } catch (e: Exception) {
            Toast.makeText(this, "Layout Error: ${e.message}", Toast.LENGTH_LONG).show()
            return
        }

        // 🔥 Safe button references
        val beekeeper = findViewById<Button?>(R.id.btnBeekeeper)
        val farmer = findViewById<Button?>(R.id.btnFarmer)

        // ❗ Prevent crash if ID missing
        if (beekeeper == null || farmer == null) {
            Toast.makeText(this, "UI Error: Check button IDs", Toast.LENGTH_LONG).show()
            return
        }

        // 🐝 BEEKEEPER
        beekeeper.setOnClickListener {
            openLogin("beekeeper")
        }

        // 🌾 FARMER
        farmer.setOnClickListener {
            openLogin("farmer")
        }
    }

    // 🔥 Common function (clean + reusable)
    private fun openLogin(role: String) {
        try {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("role", role)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Navigation Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}