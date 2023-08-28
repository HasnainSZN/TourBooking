package com.example.tourbooking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class RecyclerViewAdapter2(
    private val destinationDataList: List<DestinationData>
) :
    RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.destination_list, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destinationData = destinationDataList[position]
        holder.destinationIV.setImageResource(destinationData.imgSrcMain)
        holder.cityHeading.setText(destinationData.locationName)
    }
    override fun getItemCount(): Int {
        return destinationDataList.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var destinationIV: ImageView
        var cityHeading: TextView
        init {
            destinationIV = itemView.findViewById(R.id.destinationIV)
            cityHeading = itemView.findViewById(R.id.cityHeading)
        }
    }
}
