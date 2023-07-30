package com.example.mapexample

import android.app.Application
import com.google.android.libraries.places.api.Places

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Initialize Places API with your API key
        Places.initialize(applicationContext, "AIzaSyBSNyp6GQnnKlrMr7hD2HGiyF365tFlK5U")
    }
}
