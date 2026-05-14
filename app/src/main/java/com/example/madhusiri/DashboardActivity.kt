package com.example.madhusiri

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    // ✅ STORE ROLE
    private var role = "farmer"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        // ✅ GET ROLE FROM LOGIN
        role =
            intent.getStringExtra("role")
                ?: "farmer"

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

        // ✅ BEEKEEPER DASHBOARD
        if (role == "beekeeper") {

            btnSpray.visibility = View.GONE

            btnAddHive.visibility = View.VISIBLE

            btnHealth.visibility = View.VISIBLE
        }

        // ✅ FARMER DASHBOARD
        else {

            btnAddHive.visibility = View.GONE

            btnHealth.visibility = View.GONE

            btnSpray.visibility = View.VISIBLE
        }

        // 📍 VIEW MAP
        btnMap.setOnClickListener {

            val intent =
                Intent(
                    this,
                    MapActivity::class.java
                )

            // ✅ SEND ROLE AGAIN
            intent.putExtra(
                "role",
                role
            )

            startActivity(intent)
        }

        // 🐝 ADD HIVE
        btnAddHive.setOnClickListener {

            val intent =
                Intent(
                    this,
                    AddHiveActivity::class.java
                )

            // ✅ SEND ROLE AGAIN
            intent.putExtra(
                "role",
                role
            )

            startActivity(intent)
        }

        // 📊 HEALTH
        btnHealth.setOnClickListener {

            val intent =
                Intent(
                    this,
                    HealthActivity::class.java
                )

            intent.putExtra(
                "role",
                role
            )

            startActivity(intent)
        }

        // 🚨 SPRAY
        btnSpray.setOnClickListener {

            val intent =
                Intent(
                    this,
                    AlertActivity::class.java
                )

            intent.putExtra(
                "role",
                role
            )

            startActivity(intent)
        }

        // 🌿 TIPS
        btnTips.setOnClickListener {

            val intent =
                Intent(
                    this,
                    TipsActivity::class.java
                )

            intent.putExtra(
                "role",
                role
            )

            startActivity(intent)
        }
    }
}