package com.example.interventionroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plombierTab")
data class Plombier(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "nom_plombier") val nom:String,
    @ColumnInfo(name = "prenom_plombier") val prenom:String
)