package com.example.ivroom

import androidx.room.*
import com.example.ivroom.Type

@Dao
interface TypeDao {
    @Query("SELECT * FROM typeTab")
    fun getAll(): List<Type>

    @Query("SELECT * FROM typeTab WHERE id= :id_ty LIMIT 1")
    fun getById(id_ty:Int): Type

    @Query("SELECT * FROM typeTab WHERE intitule_type LIKE :intitule LIMIT 1")
    fun getByIntitule(intitule:String): Type

    @Insert
    fun insertAll(tyList:List<Type>)
    @Insert
    fun insert(ty: Type)

    @Delete
    fun delete(type: Type)

    @Update
    fun update(type: Type)
}