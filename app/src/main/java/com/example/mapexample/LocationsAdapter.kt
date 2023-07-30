package com.example.mapexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocationsAdapter(private val locationsList: List<Location>) :
    RecyclerView.Adapter<LocationsAdapter.LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locationsList[position]
        holder.bind(location)
    }

    override fun getItemCount(): Int {
        return locationsList.size
    }

    inner class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewLocationName: TextView = itemView.findViewById(R.id.textViewLocationName)
        private val textViewLatitude: TextView = itemView.findViewById(R.id.textViewLatitude)
        private val textViewLongitude: TextView = itemView.findViewById(R.id.textViewLongitude)

        fun bind(location: Location) {
            textViewLocationName.text = location.name
            textViewLatitude.text = "Latitude: ${location.latitude}"
            textViewLongitude.text = "Longitude: ${location.longitude}"
        }
    }
}
