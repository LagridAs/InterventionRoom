package com.example.interventionroom

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.room.Room

class InterventionRepository(applicationContext: Context) {
    /*private var db:AppDataBase = Room.databaseBuilder(
       applicationContext,
       AppDataBase::class.java, "interventionDB"
   ).fallbackToDestructiveMigration().build()
    private var ivDao: InterventionDao=db.interventionDao()
    private var list:LiveData<List<Intervention>> =ivDao.getAll()

    fun getAll():LiveData<List<Intervention>>{
        return list
    }*/


}