package com.example.interventionroom

import androidx.room.*
import java.time.LocalDate

@Dao
interface InterventionDao {
        @Query("SELECT * FROM interventionTab")
        fun getAll(): List<Intervention>

        @Query("SELECT * FROM interventionTab WHERE id= :id_inter")
        fun getByIds(id_inter:Int): List<Intervention>

        @Query("SELECT * FROM interventionTab WHERE date_Iv= :date")
        fun getAllByDate(date:String): List<Intervention>

        @Query("SELECT * FROM interventionTab WHERE id IN (:InterventionIds)")
        fun loadAllByIds(InterventionIds: IntArray): List<Intervention>

        @Insert
        fun insertAll(interList:List<Intervention>)
        @Insert
        fun insert(inter:Intervention)

        @Delete
        fun delete(intervention: Intervention)

        @Update
        fun update(intervention: Intervention)
}