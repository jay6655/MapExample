package com.example.mapexample

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.mapexample.databinding.ActivityLocationSearchBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class LocationSearchActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private var address: Address? = null
    lateinit var binding: ActivityLocationSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapLocation) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val location: String = binding.searchView.query.toString()
                var addressList: List<Address>? = null
                // on below line we are creating and initializing a geo coder.
                val geocoder = Geocoder(this@LocationSearchActivity)
                try {
                    addressList = geocoder.getFromLocationName(location, 1)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                address = addressList!![0]
                val latLng = LatLng(address!!.latitude, address!!.longitude)

                // on below line we are adding marker to that position.
                mMap?.addMarker(MarkerOptions().position(latLng).title(location))

                // below line is to animate camera to that position.
                mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                address = null
                return false
            }
        })
        mapFragment.getMapAsync(this)

        binding.btnAdd.setOnClickListener {
            if (address != null) {
                LocationListActivity.locationsList.add(
                    Location(
                        id = "1",
                        name = address!!.locality,
                        latitude = address!!.latitude,
                        longitude = address!!.longitude
                    )
                )
                finish()

            }
        }

    }

    override fun onMapReady(p0: GoogleMap) {
        println("OnMap Ready Called ...")
        mMap = p0
    }
}