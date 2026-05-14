package com.example.madhusiri

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HealthActivity : AppCompatActivity() {

    // ✅ STORE ROLE
    private var role = "beekeeper"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_health)

        // 🔙 SHOW TOP BACK BUTTON
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ✅ GET ROLE
        role =
            intent.getStringExtra("role")
                ?: "beekeeper"

        // ✅ VIEWS
        val spinner =
            findViewById<Spinner>(R.id.spinner)

        val btnSave =
            findViewById<Button>(R.id.btnSaveHealth)

        // ✅ HEALTH OPTIONS
        val list = arrayOf(
            "Good",
            "Average",
            "Poor"
        )

        val adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                list
            )

        spinner.adapter = adapter

        // ✅ SAVE HEALTH
        btnSave.setOnClickListener {

            val selected =
                spinner.selectedItem.toString()

            Toast.makeText(
                this,
                "Health Saved: $selected ✅",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // 🔙 TOP BACK BUTTON
    override fun onSupportNavigateUp(): Boolean {

        finish()

        return true
    }
}