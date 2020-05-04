package com.example.interventionroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "typeTab")
data class Type (
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "intitule_type") val intitulé:String
)