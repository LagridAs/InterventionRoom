package com.example.interventionroom

import androidx.room.*
import java.time.LocalDate
@Dao
interface PlombierDao {
    @Query("SELECT * FROM plombierTab")
    fun getAll(): List<Plombier>

    @Query("SELECT * FROM plombierTab WHERE id= :id_plm")
    fun getById(id_plm:Int): Plombier

    @Query("SELECT * FROM plombierTab WHERE prenom_plombier LIKE :first AND " +
            "nom_plombier LIKE :last LIMIT 1")
    fun getByName(first:String,last:String): Plombier

    @Insert
    fun insertAll(plomList:List<Plombier>)
    @Insert
    fun insert(plom:Plombier)
    @Delete
    fun delete(plombier: Plombier)

    @Update
    fun update(plombier: Plombier)
}