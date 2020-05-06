package com.example.ivroom

import androidx.room.*
import com.example.ivroom.Plombier

@Dao
interface PlombierDao {
    @Query("SELECT * FROM plombierTab")
    fun getAll(): List<Plombier>

    @Query("SELECT * FROM plombierTab WHERE id= :id_plm LIMIT 1")
    fun getById(id_plm:Int): Plombier

    @Query("SELECT * FROM plombierTab WHERE prenom_plombier LIKE :first AND " +
            "nom_plombier LIKE :last LIMIT 1")
    fun getByName(first:String,last:String): Plombier

    @Query("SELECT * FROM plombierTab WHERE  prenom_plombier || ' ' || nom_plombier  LIKE :fullName OR nom_plombier || ' ' || prenom_plombier LIKE :fullName LIMIT 1")
    fun getByFullName(fullName:String): Plombier

    @Insert
    fun insertAll(plomList:List<Plombier>)
    @Insert
    fun insert(plom: Plombier)
    @Delete
    fun delete(plombier: Plombier)

    @Update
    fun update(plombier: Plombier)
}