package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.adapters.SubjectsRecAdapter
import com.example.wingstofly.databinding.FragmentSharedBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants


class SharedFragment : Fragment() {
    private lateinit var bind: FragmentSharedBinding
    private lateinit var scholar:Scholar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            scholar = it.getSerializable("scholar") as Scholar
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bind = FragmentSharedBinding.inflate(layoutInflater)

        bind.cutOff.text = "Scholar cut off: ${Constants.CUT_POINT}"

        bind.meanGrade.text = " Your mean grade is ${scholar.meanGrade}"
        if (Constants.CUT_POINT_OFF_POINTS < scholar.meanAGP){
            bind.studentDeviation.text = "Congrats you are on the right track"
        }else if (Constants.CUT_POINT_OFF_POINTS == scholar.meanAGP){
            bind.studentDeviation.text = "Hey, You are treading on a dangerous zone!!"
        }else{
            var deviation = scholar.calculateDeviation() * scholar.currentSubjects.size
            bind.studentDeviation.text = "Add more than $deviation marks in each subject to be on the safe side."
        }
        setUpRecyclerView()
        return bind.root
    }

    private fun setUpRecyclerView() {
        val adp = SubjectsRecAdapter()
        adp.asyncList.submitList(scholar.currentSubjects)

        bind.subRecView.apply {
            adapter = adp
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Scholar) =
            SharedFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("scholar", param1)
                }
            }
    }

}