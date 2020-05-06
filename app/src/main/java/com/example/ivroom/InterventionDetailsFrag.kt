package com.example.ivroom

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.Serializable

class InterventionDetailsFrag : Fragment() {
    private var v: View?=null
    private lateinit var commm: Communicator
    private var supprimerBtn: Button?=null
    private var modifierBtn:Button?=null
    private var typeView: TextView?=null
    private var numeroView:TextView?=null
    private var dateView:TextView?=null
    private var plombierView:TextView?=null
    private lateinit var intervention: Intervention
    private lateinit var tyItervention: Type
    private lateinit var plomIntervention: Plombier
    val db = context?.let { AppDataBase(it) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        v=inflater.inflate(R.layout.fragment_intervention_details, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        typeView?.text=tyItervention.intitulé
        numeroView?.text=intervention.id.toString()
        plombierView?.text=plomIntervention.nom.plus(" ").plus(plomIntervention.prenom)
        dateView?.text=intervention.date
        supprimerBtn?.setOnClickListener { supprimerDialog() }
        modifierBtn?.setOnClickListener {
            GlobalScope.launch {
                val frag= EditInterventionFrag()
                val bundle=Bundle()
                val iv= intervention as Serializable
                bundle.putSerializable("intervention",iv)
                bundle.putSerializable("type",tyItervention)
                bundle.putSerializable("plombier",plomIntervention)
                frag.arguments= bundle
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.mainAct,frag)
                }
        }
    }

    private fun init(){
        val idIv=arguments?.getSerializable("interventionId") as Int
        intervention= db?.interventionDao()?.getById(idIv)!!
        GlobalScope.launch {tyItervention = db?.typeDao()?.getById(intervention.type)!!}
        GlobalScope.launch {plomIntervention = db?.plombierDao()?.getById(intervention.plombier)!!}
        commm= activity as Communicator
        supprimerBtn= v?.findViewById(R.id.suppIv)
        modifierBtn=v?.findViewById(R.id.modifIv)
        typeView=v?.findViewById(R.id.typeTxt)
        numeroView=v?.findViewById(R.id.numeroTxt)
        dateView=v?.findViewById(R.id.dateTxt)
        plombierView=v?.findViewById(R.id.plombierTxt)
    }

    private fun supprimerDialog() {
        val alertDialog: AlertDialog
        val inflater: LayoutInflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_supprimer_view, null)

        val confirmerBtn = dialogView.findViewById<Button>(R.id.confSupp)
        confirmerBtn.setOnClickListener {
            GlobalScope.launch {
            intervention.let { it1 -> suppIntervention(it1) }}
        }
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
        dialogBuilder.setOnDismissListener {commm.passListIv("")}
        dialogBuilder.setView(dialogView)
        alertDialog = dialogBuilder.create()
        //alertDialog.window!!.getAttributes().windowAnimations = R.style.PauseDialogAnimation
        alertDialog.show()
    }

    private fun suppIntervention(inter: Intervention)
    {
       db?.interventionDao()?.delete(inter)
    }



    companion object {
        fun newInstance(): InterventionDetailsFrag {
            return InterventionDetailsFrag()
        }
    }
}
