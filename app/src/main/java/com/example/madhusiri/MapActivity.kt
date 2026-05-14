package com.example.madhusiri

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    private lateinit var fusedClient:
            FusedLocationProviderClient

    // ✅ STORE ROLE
    private var role = "farmer"

    // ✅ SELECTED LOCATION
    private var selectedLatLng: LatLng? = null

    // ✅ PLACE NAME
    private var selectedPlaceName =
        "Selected Farm Location"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_map)

        // ✅ GET ROLE
        role =
            intent.getStringExtra("role")
                ?: "farmer"

        // 🔙 BACK BUTTON
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ✅ LOCATION CLIENT
        fusedClient =
            LocationServices
                .getFusedLocationProviderClient(this)

        // ✅ MAP
        val mapFragment =
            supportFragmentManager
                .findFragmentById(R.id.map)
                    as SupportMapFragment

        mapFragment.getMapAsync(this)

        // ✅ SEARCH BOX
        val etSearch =
            findViewById<EditText>(R.id.etSearch)

        // ✅ LOCATION BUTTON
        val btnLocation =
            findViewById<Button>(R.id.btnLocation)

        // 🔍 SEARCH LOCATION
        etSearch.setOnEditorActionListener {
                _, actionId, event ->

            val isSearchPressed =

                actionId ==
                        EditorInfo.IME_ACTION_SEARCH ||

                        actionId ==
                        EditorInfo.IME_ACTION_DONE ||

                        event?.keyCode ==
                        KeyEvent.KEYCODE_ENTER

            if (isSearchPressed) {

                val locationName =
                    etSearch.text.toString().trim()

                if (locationName.isNotEmpty()) {

                    try {

                        val geocoder =
                            Geocoder(
                                this,
                                Locale.getDefault()
                            )

                        val addressList =
                            geocoder.getFromLocationName(
                                locationName,
                                1
                            )

                        if (!addressList.isNullOrEmpty()) {

                            val address =
                                addressList[0]

                            val latLng =
                                LatLng(
                                    address.latitude,
                                    address.longitude
                                )

                            // ✅ SAVE LOCATION
                            selectedLatLng = latLng

                            selectedPlaceName =
                                locationName

                            // ✅ CLEAR OLD MARKERS
                            map.clear()

                            // ✅ FARM MARKER
                            map.addMarker(
                                MarkerOptions()
                                    .position(latLng)
                                    .title("Farm / Field Location")
                                    .snippet(locationName)
                                    .icon(
                                        BitmapDescriptorFactory
                                            .defaultMarker(
                                                BitmapDescriptorFactory
                                                    .HUE_GREEN
                                            )
                                    )
                            )

                            // ✅ CAMERA MOVE
                            map.animateCamera(
                                CameraUpdateFactory
                                    .newLatLngZoom(
                                        latLng,
                                        15f
                                    )
                            )

                            Toast.makeText(
                                this,
                                "Farm Location Found ✅",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {

                            Toast.makeText(
                                this,
                                "Location Not Found ❌",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } catch (e: Exception) {

                        Toast.makeText(
                            this,
                            "Error: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                true

            } else {
                false
            }
        }

        // 📍 SET LOCATION BUTTON
        btnLocation.setOnClickListener {

            // ✅ SELECTED LOCATION
            if (selectedLatLng != null) {

                val intent =
                    Intent(
                        this,
                        LocationResultActivity::class.java
                    )

                intent.putExtra(
                    "lat",
                    selectedLatLng!!.latitude
                )

                intent.putExtra(
                    "lng",
                    selectedLatLng!!.longitude
                )

                intent.putExtra(
                    "place",
                    selectedPlaceName
                )

                intent.putExtra(
                    "role",
                    role
                )

                startActivity(intent)

            } else {

                // ✅ CURRENT LOCATION
                getCurrentLocation()
            }
        }
    }

    // 🗺️ MAP READY
    override fun onMapReady(
        googleMap: GoogleMap
    ) {

        map = googleMap

        // ✅ DEFAULT FARM LOCATION
        val farmLocation =
            LatLng(13.4010, 78.0517)

        // ✅ FARM MARKER
        map.addMarker(
            MarkerOptions()
                .position(farmLocation)
                .title("Farm / Field Location")
                .snippet("Chintamani Farm")
                .icon(
                    BitmapDescriptorFactory
                        .defaultMarker(
                            BitmapDescriptorFactory.HUE_GREEN
                        )
                )
        )

        // ✅ MOVE CAMERA
        map.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(
                    farmLocation,
                    12f
                )
        )

        // ✅ MAP CLICK
        map.setOnMapClickListener {

            selectedLatLng = it

            selectedPlaceName =
                "Selected Farm Location"

            map.clear()

            map.addMarker(
                MarkerOptions()
                    .position(it)
                    .title("Farm / Field")
                    .icon(
                        BitmapDescriptorFactory
                            .defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN
                            )
                    )
            )

            map.animateCamera(
                CameraUpdateFactory
                    .newLatLngZoom(
                        it,
                        15f
                    )
            )

            Toast.makeText(
                this,
                "Farm Location Selected 📍",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // 📍 CURRENT LOCATION
    private fun getCurrentLocation() {

        if (
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                101
            )

            return
        }

        val request =
            LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                1000
            )
                .setMaxUpdates(1)
                .build()

        fusedClient.requestLocationUpdates(

            request,

            object : LocationCallback() {

                override fun onLocationResult(
                    result: LocationResult
                ) {

                    val location =
                        result.lastLocation

                    if (location != null) {

                        val currentLocation =
                            LatLng(
                                location.latitude,
                                location.longitude
                            )

                        // ✅ CURRENT LOCATION MARKER
                        map.addMarker(
                            MarkerOptions()
                                .position(currentLocation)
                                .title("Current Location")
                        )

                        // ✅ CAMERA
                        map.animateCamera(
                            CameraUpdateFactory
                                .newLatLngZoom(
                                    currentLocation,
                                    16f
                                )
                        )

                        // ✅ RESULT SCREEN
                        val intent =
                            Intent(
                                this@MapActivity,
                                LocationResultActivity::class.java
                            )

                        intent.putExtra(
                            "lat",
                            currentLocation.latitude
                        )

                        intent.putExtra(
                            "lng",
                            currentLocation.longitude
                        )

                        intent.putExtra(
                            "place",
                            "Current Location"
                        )

                        intent.putExtra(
                            "role",
                            role
                        )

                        startActivity(intent)
                    }

                    fusedClient
                        .removeLocationUpdates(this)
                }
            },

            mainLooper
        )
    }

    // 🔐 PERMISSION RESULT
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )

        if (
            requestCode == 101 &&
            grantResults.isNotEmpty() &&
            grantResults[0] ==
            PackageManager.PERMISSION_GRANTED
        ) {

            getCurrentLocation()

        } else {

            Toast.makeText(
                this,
                "Permission Denied ❌",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // 🔙 BACK BUTTON FIX
    override fun onBackPressed() {

        val intent =
            Intent(
                this,
                DashboardActivity::class.java
            )

        // ✅ RETURN SAME ROLE
        intent.putExtra(
            "role",
            role
        )

        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_NEW_TASK

        startActivity(intent)

        finish()
    }

    // 🔙 TOP BACK BUTTON
    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()

        return true
    }
}