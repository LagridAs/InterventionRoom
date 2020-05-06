package com.example.ivroom

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class AddInterventionFrag : Fragment() {
    var v:View?=null
    var numEdit:EditText?= null
    var tySpinner:Spinner?= null
    var plSpinner:Spinner?= null
    var dtImage:EditText?=null
    var confBtn:Button?=null
    var comm: Communicator?=null
    val db= context?.let { AppDataBase(it) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_add_intervention, container, false)
        return v
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        confBtn?.setOnClickListener {
            val numIv= numEdit?.text.toString()
            val dateeIv = dtImage?.text.toString()
                    //
            var typeIv: Type?= null
            var plomIv: Plombier?= null
            val ty: String = tySpinner?.selectedItem.toString()
            val plom: String = plSpinner?.selectedItem.toString()

            typeIv= db?.typeDao()?.getByIntitule(ty)
            plomIv= db?.plombierDao()?.getByFullName(plom)

            val inter=
                plomIv?.id?.let { it1 ->
                    typeIv?.id?.let { it2 ->
                        Intervention(Integer.parseInt(numIv),dateeIv,
                            it1, it2
                        )
                    }
                }
                    //
            if (inter != null) {
                addIntervention(inter)
            }

        }
    }
    fun init(){
        comm= activity as Communicator
        numEdit= v?.findViewById(R.id.numEditAj)
        dtImage= v?.findViewById(R.id.dateImgAj)
        confBtn= v?.findViewById(R.id.confAjou)
        //spinner
        val plombierNames:ArrayList<String> = arrayListOf()
        val plombiers = listPlombier()
        if (plombiers != null) {
            for (element in plombiers){
                plombierNames.add(element.nom.plus(" ").plus(element.prenom))
            }
        }

        // Initializing an ArrayAdapter
        val adapter = context?.let {
            ArrayAdapter(
                it, // Context
                android.R.layout.simple_spinner_item, // Layout
                plombierNames // Array
            )
        }

        // Set the drop down view resource
        adapter?.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with dapter
        plSpinner= v?.findViewById<Spinner>(R.id.plSpinnerAj)
        plSpinner?.adapter = adapter
        //fin spinner
        //spinner
        val typeNames:ArrayList<String> = arrayListOf()
        val types = listType()
        if (types != null) {
            for (element in types){
                typeNames.add(element.intitulé)
            }
        }

        // Initializing an ArrayAdapter
        val adapterT = context?.let {
            ArrayAdapter(
                it, // Context
                android.R.layout.simple_spinner_item, // Layout
                typeNames // Array
            )
        }

        // Set the drop down view resource
        adapterT?.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with dapter
        tySpinner= v?.findViewById<Spinner>(R.id.typeSpinnerAj)
        tySpinner?.adapter = adapterT
        //fin spinner
    }
        fun addIntervention(intervention: Intervention){
            GlobalScope.launch {
                db?.interventionDao()?.insert(intervention)
            }

        }
    fun listPlombier(): List<Plombier>?
    {
        val list =db?.plombierDao()?.getAll()
        return list
    }
    fun listType(): List<Type>? {
        val list =db?.typeDao()?.getAll()
        return list
    }



    companion object {
        fun newInstance(): AddInterventionFrag {
            return AddInterventionFrag()
        }
    }
}
