package com.example.interventionroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "interventionTab",foreignKeys = arrayOf (ForeignKey (entity = Plombier :: class,
    parentColumns = arrayOf ("id"),
    childColumns = arrayOf ("plombier_id"),
    onDelete = ForeignKey. CASCADE ,
    onUpdate = ForeignKey. CASCADE),ForeignKey (entity = Type :: class,
    parentColumns = arrayOf ("id"),
    childColumns = arrayOf ("type_id"),
    onDelete = ForeignKey. CASCADE ,
    onUpdate = ForeignKey. CASCADE)))
data class Intervention (
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "date_Iv") var date: String,
    @ColumnInfo(name = "plombier_id") var plombier:Int,
    @ColumnInfo(name = "type_id") var type:Int
)