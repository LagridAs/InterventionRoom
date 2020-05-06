package com.example.ivroom

import androidx.fragment.app.Fragment

interface Communicator {
    fun passListIv(date: String)
    fun passInterv(inter: Intervention, frag:Fragment)


}