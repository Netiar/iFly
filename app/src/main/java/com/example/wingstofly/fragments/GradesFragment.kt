package com.example.wingstofly.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.SingleActivity
import com.example.wingstofly.adapters.GradesRecAdapter
import com.example.wingstofly.adapters.JobDetailsRecView
import com.example.wingstofly.adapters.JobsRecView
import com.example.wingstofly.database.SchoolAdapter
import com.example.wingstofly.databinding.ChatBinding
import com.example.wingstofly.databinding.FragmentGradesBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.models.School


class GradesFragment : Fragment(R.layout.fragment_grades) {
    private lateinit var bind: ViewBinding
    private lateinit var gradesRecAdapter: GradesRecAdapter
    private lateinit var scholars: ArrayList<Scholar>
    private var form: Int = 0
    private var scholarNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            form = it.getInt("form")
            scholarNumber = it.getString("scholarNumber")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        if (form == 7) {
            scholars = (activity as SingleActivity).scholars
            var scholar: Scholar? = null
            for (scholar1 in scholars) {
                if (scholar1.pfNumber == scholarNumber) {
                    scholar = scholar1
                }
            }
            var names = scholar!!.name!!.split(" ")
            bind = ChatBinding.inflate(layoutInflater)

            //binding the scholars employment description
            if (scholar.workPlaces.isNotEmpty()) {
                (bind as ChatBinding).activity.text =
                    "${names[0]} is a ${scholar.workPlaces[0].title} at ${scholar.workPlaces[0].department} since ${scholar.workPlaces[0].postDate}. Always update your information to get more opportunities."
                (bind as ChatBinding).activityPar2.text = scholar.status
                (bind as ChatBinding).activityPart.text = scholar.workPlaces[0].department
            } else {
                (bind as ChatBinding).activity.text =
                    "${scholar.name} has not yet updated her information. Always update your information to get more opportunities."
                (bind as ChatBinding).activityPar2.text = scholar.status
                (bind as ChatBinding).activityPart.text = "Not updated"
            }

            //binding the scholars skill set
            if (scholar.skillSet.isNullOrBlank()) {
                (bind as ChatBinding).activityPart1.text = "Not updated"

            } else {
                (bind as ChatBinding).activityPart1.text = scholar.skillSet

            }
            (bind as ChatBinding).activity1.text =
                "Dear ${scholar.name}, we would love to tell everyone what you are good at or what you would like to do."

            //binding the scholars employment history
            (bind as ChatBinding).details.text =
                "${names[names.size - 1]} has been involved in the following positions. "

            if (scholar.workPlaces.isNotEmpty()) {
                val adp1 = JobsRecView(requireContext())
                adp1.asyncList.submitList(scholar.workPlaces)
                (bind as ChatBinding).view.apply {
                    adapter = adp1
                    layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                }

            } else {
                (bind as ChatBinding).anim.isVisible = true
                (bind as ChatBinding).warning.isVisible = true
            }
            (bind as ChatBinding).act.text =
                "Learning never stops. These are institutions that ${names[names.size - 1]} attended after high school studies."

            //forming a string literal of the student university
            if (scholar.tertiaryInstitutions.size != 0){
                val adp = SchoolAdapter(scholar.tertiaryInstitutions)

                (bind as ChatBinding).recView.apply {
                    adapter = adp
                    layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                    setHasFixedSize(true)
                }
            }
        //displaying high school grades
        } else {
            val formScholars = ArrayList<Scholar>()
            scholars = (activity as MainActivity).scholars
            for (i in 0 until scholars.size) {
                if (scholars[i].form == form) {
                    formScholars.add(scholars[i])

                }

            }
            bind = FragmentGradesBinding.inflate(inflater)
            (bind as FragmentGradesBinding).grades.text = "Form $form grades"
            gradesRecAdapter = GradesRecAdapter()
            gradesRecAdapter.asyncListDiffer.submitList(formScholars)
            (bind as FragmentGradesBinding).gradesRec.apply {
                adapter = gradesRecAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
        }
        return bind.root
    }

    companion object {
        fun newInstance(form: Int, scholarNumber: String) =
            GradesFragment().apply {
                arguments = Bundle().apply {
                    putInt("form", form)
                    putString("scholarNumber",scholarNumber)
                }
            }
    }


}