package com.example.tourbooking

import android.os.Parcel
import android.os.Parcelable

class TourData(
        val imgSrcMain: Int,
        val tourName: String?,
        val imgSrcLocation: Int,
        val startLocation: String?,
        val startDate: String?,
        val starCount:Int,
        val costOftour:Int,
        val properStartDestination:String?,
        val departureTime:String?,
        val description:Int,
        val imagesList:ArrayList<Int>
) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
                TODO("imagesList")
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(imgSrcMain)
                parcel.writeString(tourName)
                parcel.writeInt(imgSrcLocation)
                parcel.writeString(startLocation)
                parcel.writeString(startDate)
                parcel.writeInt(starCount)
                parcel.writeInt(costOftour)
                parcel.writeString(properStartDestination)
                parcel.writeString(departureTime)
                parcel.writeInt(description)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<TourData> {
                override fun createFromParcel(parcel: Parcel): TourData {
                        return TourData(parcel)
                }

                override fun newArray(size: Int): Array<TourData?> {
                        return arrayOfNulls(size)
                }
        }
}
