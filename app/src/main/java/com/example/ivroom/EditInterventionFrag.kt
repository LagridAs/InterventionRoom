package com.example.ivroom

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditInterventionFrag : Fragment() {
    private var v: View?=null
    var numEdit: TextView?= null
    var typeEdit: EditText?= null
    var plnomEdit:EditText?= null
    var plprenomEdit:EditText?= null
    var dtEdit:EditText?=null
    var confBtn: Button?=null
    private lateinit var tyIntervention: Type
    private lateinit var plIntervention: Plombier
    private lateinit var comm: Communicator
    private lateinit var intervention: Intervention
    var db= context?.let { AppDataBase(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_intervention, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        confBtn?.setOnClickListener {
            val ty= typeEdit?.text.toString()
            val plomNom= plnomEdit?.text.toString()
            val plomPrenom= plprenomEdit?.text.toString()
            val dateIv= dtEdit?.text.toString()
            val plombier=db?.plombierDao()?.getByName(plomPrenom,plomPrenom)
            val type=db?.typeDao()?.getByIntitule(ty)
            val NV= plombier?.id?.let { it1 -> type?.id?.let { it2 ->
                Intervention(intervention.id,dateIv, it1,
                    it2
                )
            } }
            if (NV != null) {
                editIntervention(NV)
            }
        }



    }
    fun init(){
        intervention= arguments?.getSerializable("intervention") as Intervention
        tyIntervention= arguments?.getSerializable("type") as Type
        plIntervention= arguments?.getSerializable("plombier") as Plombier

        numEdit= v?.findViewById(R.id.numTxMod)
        typeEdit=v?.findViewById(R.id.typeMod)
        plnomEdit=v?.findViewById(R.id.plombierNMod)
        plprenomEdit=v?.findViewById(R.id.plombierPMod)
        dtEdit=v?.findViewById(R.id.dateMod)
        confBtn=v?.findViewById(R.id.confEdit)

        numEdit?.text= intervention.id.toString()
        typeEdit?.text = Editable.Factory.getInstance().newEditable(tyIntervention.intitulé)
        plnomEdit?.text = Editable.Factory.getInstance().newEditable(plIntervention.nom)
        plprenomEdit?.text = Editable.Factory.getInstance().newEditable(plIntervention.prenom)
        dtEdit?.text = Editable.Factory.getInstance().newEditable(intervention.date)
    }
    fun editIntervention(interNv: Intervention)
    {
        GlobalScope.launch {db?.interventionDao()?.update(interNv)}
    }

    companion object {
        fun newInstance(): EditInterventionFrag {
            return EditInterventionFrag()
        }

    }

}
