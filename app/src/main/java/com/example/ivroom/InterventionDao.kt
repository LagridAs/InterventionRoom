package com.example.ivroom

import androidx.room.*
import com.example.ivroom.Intervention

@Dao
interface InterventionDao {
        @Query("SELECT * FROM interventionTab")
        fun getAll(): List<Intervention>

        @Query("SELECT * FROM interventionTab WHERE id= :id_iv LIMIT 1")
        fun getById(id_iv:Int): Intervention

        @Query("SELECT * FROM interventionTab WHERE date_Iv LIKE :date")
        fun getAllByDate(date:String): List<Intervention>

        @Insert
        fun insertAll(interList:List<Intervention>)

        @Insert
        fun insert(inter: Intervention)

        @Delete
        fun delete(intervention: Intervention)

        @Update
        fun update(intervention: Intervention)
}