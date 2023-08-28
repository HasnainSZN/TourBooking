package com.example.tourbooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (private val dataList: ArrayList<TourData>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private lateinit var rvaListener : onItemClickListener
    interface onItemClickListener{
        fun onItemClick(tourItemDto: TourData){
        }
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        rvaListener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tour_list, parent, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tourDto = dataList[position]
        holder.imgSrcMain.setImageResource(tourDto.imgSrcMain)
        holder.tourName.setText(tourDto.tourName)
        holder.imgSrcLocation.setImageResource(tourDto.imgSrcLocation)
        holder.startLocation.setText(tourDto.startLocation)
        holder.startDate.setText(tourDto.startDate)
        holder.startTime.setText(tourDto.departureTime)
        holder.itemView.setOnClickListener {
                rvaListener.onItemClick(tourDto)
            }
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgSrcMain: ImageView= itemView.findViewById(R.id.imgOfCity)
        val tourName:TextView = itemView.findViewById(R.id.tourHeading)
        val imgSrcLocation:ImageView = itemView.findViewById(R.id.startDestinationIV)
        val startLocation:TextView = itemView.findViewById(R.id.startDestinationHeading)
        val startDate:TextView = itemView.findViewById(R.id.startDateHeading)
        val startTime:TextView = itemView.findViewById(R.id.startTime)
    }
}