package com.example.interventionroom

import androidx.room.*

@Dao
interface TypeDao {
    @Query("SELECT * FROM typeTab")
    fun getAll(): List<Type>

    @Query("SELECT * FROM typeTab WHERE id= :id_ty")
    fun getById(id_ty:Int): Type

    @Query("SELECT * FROM typeTab WHERE intitule_type LIKE :intitule")
    fun getByIntitule(intitule:String): List<Type>

    @Insert
    fun insertAll(tyList:List<Type>)
    @Insert
    fun insert(ty:Type)

    @Delete
    fun delete(type: Type)

    @Update
    fun update(type: Type)
}