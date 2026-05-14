package com.example.madhusiri

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FarmerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmer)

        // 📍 Map Button
        val btnMap = findViewById<Button>(R.id.btnMap)
        btnMap.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }

        // 🌾 Spray Button
        val btnSpray = findViewById<Button>(R.id.btnSpray)
        btnSpray.setOnClickListener {
            startActivity(Intent(this, AlertActivity::class.java))
        }
    }
}