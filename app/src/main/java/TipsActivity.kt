package com.example.madhusiri

import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TipsActivity : AppCompatActivity() {

    // ✅ STORE ROLE
    private var role = "beekeeper"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // 🔙 SHOW TOP BACK BUTTON
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ✅ GET ROLE
        role =
            intent.getStringExtra("role")
                ?: "beekeeper"

        // ✅ SCROLL VIEW
        val scrollView =
            ScrollView(this)

        // ✅ MAIN LAYOUT
        val layout =
            LinearLayout(this)

        layout.orientation =
            LinearLayout.VERTICAL

        layout.setPadding(
            40,
            40,
            40,
            40
        )

        layout.setBackgroundColor(
            Color.parseColor("#F5F5F5")
        )

        // 🌿 TITLE
        val title =
            TextView(this)

        title.text =
            "🌿 SAFE BEEKEEPING TIPS"

        title.textSize = 30f

        title.setTextColor(
            Color.parseColor("#2E7D32")
        )

        title.setPadding(
            0,
            0,
            0,
            40
        )

        // ✅ TIPS
        val tips = arrayOf(

            "✅ Spray pesticides in evening",

            "✅ Avoid spraying near hives",

            "✅ Inform beekeeper before spraying",

            "✅ Use organic pesticides",

            "✅ Keep bees away from chemicals"
        )

        // ✅ ADD TIPS
        for (tipText in tips) {

            val tip =
                TextView(this)

            tip.text = tipText

            tip.textSize = 24f

            tip.setTextColor(Color.BLACK)

            tip.setPadding(
                20,
                20,
                20,
                20
            )

            layout.addView(tip)
        }

        // ✅ ADD TITLE
        layout.addView(title, 0)

        // ✅ ADD LAYOUT TO SCROLLVIEW
        scrollView.addView(layout)

        // ✅ SHOW SCREEN
        setContentView(scrollView)
    }

    // 🔙 TOP BACK BUTTON
    override fun onSupportNavigateUp(): Boolean {

        finish()

        return true
    }
}