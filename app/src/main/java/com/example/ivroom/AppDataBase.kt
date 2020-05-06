package com.example.ivroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ivroom.*

@Database(entities = [Intervention::class, Plombier::class, Type::class], version = 1)
abstract class AppDataBase: RoomDatabase(){
    abstract fun interventionDao(): InterventionDao
    abstract fun plombierDao(): PlombierDao
    abstract fun typeDao(): TypeDao

    companion object {
        @Volatile private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDataBase::class.java, "interventionDB.db")
            .build()
    }
}