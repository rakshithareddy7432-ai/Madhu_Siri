package com.example.madhusiri

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BeekeeperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beekeeper)

        // 🔘 Save Hive Button
        val btnAddHive = findViewById<Button>(R.id.btnAddHive)

        btnAddHive.setOnClickListener {
            Toast.makeText(this, "Hive Saved ✅", Toast.LENGTH_SHORT).show()
        }

        // 📍 Open Map Button
        val btnMap = findViewById<Button>(R.id.btnMap)

        btnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)

            // 🔥 IMPORTANT: pass role (for map + firebase)
            intent.putExtra("role", "beekeeper")

            startActivity(intent)
        }
    }
}