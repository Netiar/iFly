package com.example.wingstofly.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.adapters.GradesRecAdapter
import com.example.wingstofly.database.Subjects
import com.example.wingstofly.databinding.FragmentGradesBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.models.Subject
import kotlinx.android.synthetic.main.fragment_grades.*

class GradesFragment: Fragment(R.layout.fragment_grades) {
    private lateinit var gradesRecAdapter: GradesRecAdapter
    private lateinit var scholars: ArrayList<Scholar>
    private lateinit var subjects: ArrayList<Subject>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity =(activity as MainActivity)
        scholars = mainActivity.scholars
        subjects = Subjects().allSubjects

//        for (scholar in scholars){
//            for (subject in subjects){
//                subject.score = 83
//                scholar.addSubject(subject)
//            }
//        }

        gradesRecAdapter = GradesRecAdapter()
        gradesRecAdapter.asyncListDiffer.submitList(scholars)
        gradesRec.apply {
            adapter = gradesRecAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

}