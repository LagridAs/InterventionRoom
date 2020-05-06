package com.example.ivroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_intervention_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.Serializable

class InterventionList : Fragment() {
    private lateinit var interadap: IntervAdapter
    private lateinit var layoutMgr: LinearLayoutManager
    lateinit var dataList:MutableList<Intervention>
    private var v: View?= null
    private lateinit var communicator: Communicator
    private var ajouter: Button?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.fragment_intervention_list, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataList = mutableListOf()
        ajouter=v?.findViewById(R.id.ajouterIv)
        dataList= arguments?.getParcelableArrayList<Intervention>("ListInter") as MutableList<Intervention>
        interadap= IntervAdapter(dataList)
        layoutMgr= LinearLayoutManager(activity)
        recyclerInterv.apply {
            adapter=interadap
            layoutManager= layoutMgr
        }
        interadap.onItemClick = { intervention ->
            GlobalScope.launch {
            val frag= InterventionDetailsFrag()
            val bundle=Bundle()
                val id: Serializable = intervention.id as Serializable
                bundle.putSerializable("interventionId",id)
                frag.arguments= bundle
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.mainAct,frag)?.commit()
            }
        }
        ajouter?.setOnClickListener {
            GlobalScope.launch {
            val fragment= AddInterventionFrag()
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainAct,fragment).commit()}
        }
    }

    companion object {
        fun newInstance(): InterventionList = InterventionList()
    }
}
