package com.example.madhusiri

import android.location.Geocoder
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class LocationResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_result)

        // 🔙 Back Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val lat = intent.getDoubleExtra("lat", 0.0)
        val lng = intent.getDoubleExtra("lng", 0.0)

        val tvLocation =
            findViewById<TextView>(R.id.tvLocation)

        try {

            val geocoder =
                Geocoder(this, Locale.getDefault())

            val addressList =
                geocoder.getFromLocation(lat, lng, 1)

            if (!addressList.isNullOrEmpty()) {

                val address = addressList[0]

                val areaName =
                    address.locality ?: "Unknown Area"

                val fullAddress =
                    address.getAddressLine(0)

                tvLocation.text =

                    "📍 Selected Location\n\n" +

                            "Area Name:\n" +
                            "$areaName\n\n" +

                            "Full Address:\n" +
                            "$fullAddress\n\n" +

                            "Latitude:\n" +
                            "$lat\n\n" +

                            "Longitude:\n" +
                            "$lng\n\n" +

                            "Latitude means location from North to South.\n\n" +

                            "Longitude means location from East to West."

            } else {

                tvLocation.text =

                    "📍 Selected Location\n\n" +

                            "Latitude:\n$lat\n\n" +

                            "Longitude:\n$lng"
            }

        } catch (e: Exception) {

            tvLocation.text =

                "📍 Selected Location\n\n" +

                        "Latitude:\n$lat\n\n" +

                        "Longitude:\n$lng"
        }
    }

    // 🔙 Back Button
    override fun onSupportNavigateUp(): Boolean {

        onBackPressedDispatcher.onBackPressed()
        return true
    }
}