package com.example.wingstofly.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
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

        if(param1!!.pfNumber!!.subSequence(0,2) == "pf") {
            bind.primarySchool.text = "${param1!!.name!!.split(" ")[0]} is currently a student at ${param1!!.secondarySchool} after successfully completing her junior studies in ${param1!!.primarySchool}."
            bind.pMarks.text = "primary grade \n ${param1!!.primaryMeanGrade}"
            bind.hGrade.text = "high school grade \n ${param1!!.primaryMeanGrade}"
//            bind.deviation.text =
  //              "current deviation \npoints: ${param1!!.calculateDeviation(param1!!.meanAGP[(param1!!.meanAGP.size - 1).toString()]!!)}"
        }else{
            bind.primarySchool.text = "${param1!!.name!!.split(" ")[0]} is a former student at ${param1!!.secondarySchool} after successfully completing her junior studies in ${param1!!.primarySchool}."
            bind.pMarks.text = "primary grade \n ${param1!!.primaryMeanGrade}"
            bind.hGrade.text = "high school grade \n ${param1!!.primaryMeanGrade}"
           bind.deviation.text =
            "Deviated By \npoints: ${param1!!.calculateDeviation(param1!!.meanAGP[(param1!!.meanAGP.size - 1).toString()]!!)}"

        }
        //binding the leadership roles
        if(param1!!.leadershipRoles.size >= 3){
            bind.leader1.text = "${param1!!.leadershipRoles[0].title} - ${param1!!.leadershipRoles[0].eventOwner}"
            bind.leader2.text = "${param1!!.leadershipRoles[1].title} - ${param1!!.leadershipRoles[0].eventOwner}"
            bind.leader3.text = "${param1!!.leadershipRoles[2].title} - ${param1!!.leadershipRoles[0].eventOwner}"
        }else if (param1!!.leadershipRoles.size == 2){
            bind.leader1.text = "${param1!!.leadershipRoles[0].title} - ${param1!!.leadershipRoles[0].eventOwner}"
            bind.leader2.text = "${param1!!.leadershipRoles[1].title} - ${param1!!.leadershipRoles[0].eventOwner}"
            bind.leader3.text = "Not updated"

        }else if(param1!!.leadershipRoles.size == 1){
            bind.leader1.text = "${param1!!.leadershipRoles[0].title} - ${param1!!.leadershipRoles[0].eventOwner}"
            bind.leader2.text = "Not updated"
            bind.leader3.text = "Not updated"
        }else{
            bind.leader1.text = "Not updated"
            bind.leader2.text = "Not updated"
            bind.leader3.text = "Not updated"
        }

        bind.branchOrigin.text = "${param1!!.name!!.split(" ")[0]} is a member of ${param1!!.origin} wings to fly community. Here is how you can reach her/him."
       if(param1!!.primaryNumber.isNullOrBlank() && param1!!.otherNumber.isNullOrBlank() ){
           bind.phone.text = "Primary Number: not updated \nOther Number: not updated"
       }else if(param1!!.primaryNumber!!.isNotEmpty() && param1!!.otherNumber.isNullOrBlank()){
           bind.phone.text = "Primary Number: ${param1!!.primaryNumber} \nOther Number: not updated"
       }else{
           bind.phone.text = "Primary Number: ${param1!!.primaryNumber} \nOther Number: ${param1!!.otherNumber}"
       }

        if (param1!!.emailAddress.isNullOrBlank()){
            bind.email.text = "Email : not updated"
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