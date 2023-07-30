package com.example.mapexample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.mapexample.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.isTrafficEnabled = true

        // Add markers for all locations
        for (location in LocationListActivity.locationsList) {
            val latLng = LatLng(location.latitude!!, location.longitude!!)
            mMap.addMarker(MarkerOptions().position(latLng).title(location.name))
        }

        // Draw a path connecting all locations
        val pathOptions = PolylineOptions().apply {
            addAll(LocationListActivity.locationsList.map { LatLng(it.latitude!!, it.longitude!!) })
            color(Color.BLUE)
            width(5f)
        }
        mMap.addPolyline(pathOptions)

        if (LocationListActivity.locationsList.size >= 1) {
            mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        LocationListActivity.locationsList[0].latitude!!,
                        LocationListActivity.locationsList[0].longitude!!
                    ), 10f
                )
            )
        }
    }
}