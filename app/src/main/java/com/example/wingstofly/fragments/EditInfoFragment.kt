package com.example.wingstofly.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentEditInfoBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants
import com.google.firebase.database.*


class EditInfoFragment : Fragment() {
    private lateinit var mAuth: DatabaseReference
    private lateinit var scholars: ArrayList<Scholar>
    private lateinit var bind: FragmentEditInfoBinding
    private lateinit var pref: SharedPreferences
    lateinit var scholar: Scholar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentEditInfoBinding.inflate(layoutInflater)

        mAuth = FirebaseDatabase.getInstance().reference
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        scholars = (activity as MainActivity).scholars
        val pfNumber = pref.getString(Constants.PF_NUMBER, null)


        for (databaseScholar in scholars){
            val currentPfNumber = databaseScholar.pfNumber
            if (currentPfNumber.contentEquals(pfNumber, true)){
                scholar = databaseScholar

                bind.highSchool.text = "High-School: ${scholar.secondarySchool}"
                bind.highGrade.text = "Mean-Grade: ${scholar.meanGrade}"
                bind.highMarks.text = "Mean-AGP: ${scholar.meanAGP.toString()}"
                bind.primarySchool.text = "Primary School: ${scholar.primarySchool}"
                bind.primaryMarks.text = "Total marks: ${scholar.primaryMarks.toString()}"
                bind.primaryGrade.text = "Mean-Grade: ${scholar.primaryMeanGrade}"
                bind.branchOrigin.text = "Origin Branch: ${scholar.origin}"
                bind.phone.text = "Primary Number: ${scholar.primaryNumber} \nOther Number: ${scholar.otherNumber}"
                bind.email.text = "Email : ${scholar.emailAddress}"

                if (scholar.pfNumber!!.subSequence(0,2) != "pf"){
                    bind.warning.isVisible = false
                    bind.checkText.text = scholar.varsity
                    bind.email.text = "Email : ${scholar.emailAddress}"

                }

            }

        }


        return bind.root
    }

    private fun getScholars(): ArrayList<Scholar> {
        val list = ArrayList<Scholar>()

        mAuth.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){
                    list.add(data.getValue(Scholar::class.java)!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
        return list
    }


}