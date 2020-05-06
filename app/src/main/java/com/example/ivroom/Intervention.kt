package com.example.ivroom

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "interventionTab",foreignKeys = arrayOf (ForeignKey (entity = Plombier :: class,
    parentColumns = arrayOf ("id"),
    childColumns = arrayOf ("plombier_id"),
    onDelete = ForeignKey. CASCADE ,
    onUpdate = ForeignKey. CASCADE),ForeignKey (entity = Type :: class,
    parentColumns = arrayOf ("id"),
    childColumns = arrayOf ("type_id"),
    onDelete = ForeignKey. CASCADE ,
    onUpdate = ForeignKey. CASCADE)))
data class Intervention(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "date_Iv") var date: String?,
    @ColumnInfo(name = "plombier_id") var plombier:Int,
    @ColumnInfo(name = "type_id") var type:Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(date)
        parcel.writeInt(plombier)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Intervention> {
        override fun createFromParcel(parcel: Parcel): Intervention {
            return Intervention(parcel)
        }

        override fun newArray(size: Int): Array<Intervention?> {
            return arrayOfNulls(size)
        }
    }
}