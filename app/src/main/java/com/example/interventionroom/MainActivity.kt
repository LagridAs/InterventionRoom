package com.example.interventionroom

import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val db = AppDataBase(this)

        val plomList= listOf<Plombier>(
            Plombier(1,"Lagrid","Houssem"),
            Plombier(2,"Madani","zakaria"),
            Plombier(3,"Djerad","Imad"),
            Plombier(4,"Tabib","Anes")
            )
        val tyList= listOf<Type>(
            Type(1,"Reparation"),
            Type(2,"Installation"),
            Type(3,"Verification Conformité"),
            Type(4,"Depannage"),
            Type(5,"Recherche Fuite")
            )
        val interList= listOf<Intervention>(
            Intervention(1, "2020-5-4",1,3),
            Intervention(2, "2020-5-4",2,2),
            Intervention(3, "2020-5-4",3,1),
            Intervention(4, "2020-5-6",4,4),
            Intervention(5, "2020-5-7",1,5))


        //
        GlobalScope.launch {
            //db.plombierDao().insertAll(plomList)
            val plom = db.plombierDao().getAll()

            plom?.forEach {
                println(it.nom.plus(it.prenom))
            }
        }
            GlobalScope.launch {

                //db.typeDao().insertAll(tyList)
                val ty = db.typeDao().getAll()

                ty?.forEach {
                    println(it.intitulé)
                }
            }
        GlobalScope.launch{
            //db.interventionDao().insertAll(interList)
            val iv = db.interventionDao().getAll()
            iv?.forEach{
                val type= db.typeDao().getById(it.type)
                println(type)
            }

        }

        /*val plomb=Plombier(1,"Lagrid","Houssem")
        db.plombierDao().insert(plomb)*/
        val manipule=Intervention(10, "2020-5-26",3,4)

        addBtn.setOnClickListener {
            GlobalScope.launch {
                db.interventionDao().insert(manipule)
                val iv = db.interventionDao().getAll()
                println("List apres add")
                iv?.forEach{
                    println(it.id)
                    println(it.type)
                    println(it.plombier)
                    println(it.date)
                }
            }
        }
        deleteBtn.setOnClickListener {
            GlobalScope.launch {
                db.interventionDao().delete(manipule)
                val iv = db.interventionDao().getAll()
                println("List apres delete")
                iv?.forEach{
                    println(it.id)
                    println(it.type)
                    println(it.plombier)
                    println(it.date)
                }
            }
        }
        updateBtn.setOnClickListener {
            GlobalScope.launch {
                db.interventionDao().update(Intervention(1, "2020-5-8", 1, 1))
                val iv = db.interventionDao().getAll()
                println("List apres update")
                iv?.forEach{
                    println(it.id)
                    println(it.type)
                    println(it.plombier)
                    println(it.date)
                }
            }
        }
        /*listBtn.setOnClickListener {
            val itList=db.interventionDao().getAll()
            for(item in itList)
            {
                Log.d(ContentValues.TAG,item.id.toString() + item.date.toString() + db.plombierDao().getById(item.plombier).nom +
                        db.typeDao().getById(item.type).intitulé)
            }
        }*/
        dateBtn.setOnClickListener {
            GlobalScope.launch {
                val iv = db.interventionDao().getAllByDate("2020-5-4")
                println("List de la date 2020-5-4")
                iv?.forEach{
                    println(it.id)
                    println(it.type)
                    println(it.plombier)
                    println(it.date)
                }

            }

        }

    }

}
