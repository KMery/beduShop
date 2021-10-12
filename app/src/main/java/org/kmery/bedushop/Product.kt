package org.kmery.bedushop

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import java.io.FileDescriptor.`in`
import java.io.InputStream
import java.lang.Exception
import java.net.URL

class Rating(val rating: Int = 3, val count: Int = 101): Parcelable {
    constructor(parcel: Parcel):this(parcel.readInt(), parcel.readInt()) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(rating)
        parcel.writeInt(count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rating> {
        override fun createFromParcel(parcel: Parcel): Rating {
            return Rating(parcel)
        }

        override fun newArray(size: Int): Array<Rating?> {
            return arrayOfNulls(size)
        }
    }
}

class Product (
    val title: String,
    val price: Double,
    val description: String,
    //val rating: Float,
    //val rating: Rating,
    val category: String,
    val image: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        //parcel.readFloat(),

        //parcel.readParcelable<Rating>()!!,
        //parcel.readInt()
        parcel.readString()!!,
        parcel.readString()!!
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeDouble(price)
        parcel.writeString(description)
        //parcel.writeFloat(rating)
        parcel.writeString(category)
        //parcel.writeInt(idImage)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}