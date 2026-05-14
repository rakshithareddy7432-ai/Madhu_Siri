package com.example.madhusiri

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = TextView(this)
        text.text = "No alerts yet"

        setContentView(text)
    }
}