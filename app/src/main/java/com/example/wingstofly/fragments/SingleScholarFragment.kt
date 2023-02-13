package com.example.wingstofly.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentSingleScholarBinding
import com.example.wingstofly.models.Scholar

private const val SCHOLAR = "param1"

class SingleScholarFragment : Fragment() {
    private var param1: Scholar? = null
    private lateinit var bind: FragmentSingleScholarBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(SCHOLAR) as Scholar?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        bind = FragmentSingleScholarBinding.inflate(inflater)
        bind.highSchool.text = "High-School: ${param1!!.secondarySchool}"
        bind.highGrade.text = "Mean-Grade: ${param1!!.meanGrade}"
        bind.highMarks.text = "Mean-AGP: ${param1!!.meanAGP.toString()}"
        bind.primarySchool.text = "Primary School: ${param1!!.primarySchool}"
        bind.primaryMarks.text = "Total marks: ${param1!!.primaryMarks.toString()}"
        bind.primaryGrade.text = "Mean-Grade: ${param1!!.primaryMeanGrade}"
        bind.branchOrigin.text = "Origin Branch: ${param1!!.getOrigin()}"
        bind.phone.text = "Primary Number: ${param1!!.primaryNumber} \nOther Number: ${param1!!.otherNumber}"

        if (param1!!.emailAddress?.isEmpty() == true){
            bind.createEmail.visibility = View.VISIBLE
        }else{
            bind.email.text = "Email : ${param1!!.emailAddress}"
        }
        return bind.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Scholar) =
            SingleScholarFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SCHOLAR, param1)
                }
            }
    }
}