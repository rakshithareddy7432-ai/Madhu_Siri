package com.example.madhusiri

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddHiveActivity : AppCompatActivity() {

    // ✅ STORE ROLE
    private var role = "beekeeper"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_hive)

        // 🔙 SHOW TOP BACK BUTTON
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ✅ GET ROLE
        role =
            intent.getStringExtra("role")
                ?: "beekeeper"

        // ✅ INPUTS
        val etHiveName =
            findViewById<EditText>(R.id.etHiveName)

        val etHiveId =
            findViewById<EditText>(R.id.etHiveId)

        val etHiveLocation =
            findViewById<EditText>(R.id.etHiveLocation)

        val etBeeType =
            findViewById<EditText>(R.id.etBeeType)

        val etNotes =
            findViewById<EditText>(R.id.etNotes)

        // ✅ BUTTONS
        val btnPickLocation =
            findViewById<Button>(R.id.btnPickLocation)

        val btnSaveHive =
            findViewById<Button>(R.id.btnSaveHive)

        // ✅ RESULT
        val tvHiveResult =
            findViewById<TextView>(R.id.tvHiveResult)

        // 📍 OPEN MAP
        btnPickLocation.setOnClickListener {

            val intent =
                Intent(
                    this,
                    MapActivity::class.java
                )

            // ✅ PASS ROLE
            intent.putExtra(
                "role",
                role
            )

            startActivity(intent)
        }

        // 💾 SAVE HIVE
        btnSaveHive.setOnClickListener {

            val hiveName =
                etHiveName.text.toString().trim()

            val hiveId =
                etHiveId.text.toString().trim()

            val hiveLocation =
                etHiveLocation.text.toString().trim()

            val beeType =
                etBeeType.text.toString().trim()

            val notes =
                etNotes.text.toString().trim()

            // ✅ VALIDATION
            if (
                hiveName.isEmpty() ||
                hiveId.isEmpty() ||
                hiveLocation.isEmpty() ||
                beeType.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Please Fill All Details",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                // ✅ SHOW RESULT
                tvHiveResult.text =

                    "✅ Hive Added Successfully\n\n" +

                            "🐝 Hive Name: $hiveName\n\n" +

                            "🆔 Hive ID: $hiveId\n\n" +

                            "📍 Location: $hiveLocation\n\n" +

                            "🐝 Bee Type: $beeType\n\n" +

                            "📝 Notes: $notes"

                Toast.makeText(
                    this,
                    "Hive Stored Successfully ✅",
                    Toast.LENGTH_SHORT
                ).show()

                // ✅ CLEAR FIELDS
                etHiveName.text.clear()
                etHiveId.text.clear()
                etHiveLocation.text.clear()
                etBeeType.text.clear()
                etNotes.text.clear()
            }
        }
    }

    // 🔙 TOP BACK BUTTON
    override fun onSupportNavigateUp(): Boolean {

        finish()

        return true
    }
}