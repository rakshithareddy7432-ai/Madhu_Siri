package com.example.madhusiri

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // ✅ USER ROLE
        val role = intent.getStringExtra("role") ?: "farmer"

        // 🔘 BUTTONS
        val btnMap =
            findViewById<Button>(R.id.btnMap)

        val btnHealth =
            findViewById<Button>(R.id.btnHealth)

        val btnTips =
            findViewById<Button>(R.id.btnTips)

        val btnAddHive =
            findViewById<Button>(R.id.btnAddHive)

        val btnSpray =
            findViewById<Button>(R.id.btnSpray)

        // ✅ ROLE BASED UI
        if (role == "beekeeper") {

            // ❌ Hide Spray
            btnSpray.visibility = View.GONE

        } else {

            // ❌ Hide Beekeeper Features
            btnAddHive.visibility = View.GONE
            btnHealth.visibility = View.GONE
        }

        // 📍 VIEW MAP
        btnMap.setOnClickListener {

            val intent =
                Intent(
                    this,
                    MapActivity::class.java
                )

            intent.putExtra(
                "mode",
                "view"
            )

            startActivity(intent)
        }

        // 🐝 ADD HIVE
        btnAddHive.setOnClickListener {

            val intent =
                Intent(
                    this,
                    MapActivity::class.java
                )

            intent.putExtra(
                "mode",
                "hive"
            )

            startActivity(intent)
        }

        // 📊 HEALTH
        btnHealth.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    HealthActivity::class.java
                )
            )
        }

        // 🚨 ALERT
        btnSpray.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    AlertActivity::class.java
                )
            )
        }

        // 🌿 TIPS
        btnTips.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    TipsActivity::class.java
                )
            )
        }
    }
}