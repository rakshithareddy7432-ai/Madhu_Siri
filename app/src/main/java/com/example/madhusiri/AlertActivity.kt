package com.example.madhusiri

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AlertActivity : AppCompatActivity() {

    // ✅ STORE ROLE
    private var role = "farmer"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_alert)

        // 🔙 SHOW TOP BACK BUTTON
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ✅ GET ROLE
        role =
            intent.getStringExtra("role")
                ?: "farmer"

        // ✅ TEXTVIEWS
        val tvCrop =
            findViewById<TextView>(R.id.tvCrop)

        val tvPesticide =
            findViewById<TextView>(R.id.tvPesticide)

        val tvTime =
            findViewById<TextView>(R.id.tvTime)

        val tvWeather =
            findViewById<TextView>(R.id.tvWeather)

        val tvResult =
            findViewById<TextView>(R.id.tvResult)

        // ✅ BUTTON
        val btnCheck =
            findViewById<Button>(R.id.btnCheck)

        // 🌾 CHECK SPRAYING
        btnCheck.setOnClickListener {

            val result =

                "✅ Spraying Recommended Today\n\n" +

                        "🌱 Crop: Tomato\n\n" +

                        "🧪 Suggested Pesticide: Neem Spray\n\n" +

                        "⏰ Best Time: 6:00 AM - 8:00 AM\n\n" +

                        "☁️ Weather Condition: Good"

            // ✅ SHOW RESULT
            tvResult.text = result

            Toast.makeText(
                this,
                "Daily Spraying Info Loaded ✅",
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