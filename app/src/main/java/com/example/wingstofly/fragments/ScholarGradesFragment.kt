package com.example.wingstofly.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wingstofly.MainActivity
import com.example.wingstofly.databinding.FragmentScholarGradesBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants

class ScholarGradesFragment: Fragment() {
    private lateinit var bind: FragmentScholarGradesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentScholarGradesBinding.inflate(inflater)

        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val pfNumber = pref.getString(Constants.PF_NUMBER, null)
        val scholars = (activity as MainActivity).scholars
        var scholar1: Scholar? = null

        for (scholar in scholars){
            if (scholar.pfNumber == pfNumber){
                scholar1 = scholar
            }
        }
        val fragment = ScholarFormFragment.newInstance(scholar1!!, "grades")
        val manager = childFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(bind.frameLayout.id, fragment)
        transaction.commit()

        return  bind.root
    }
}