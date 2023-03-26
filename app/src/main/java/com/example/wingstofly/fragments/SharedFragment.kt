package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.wingstofly.adapters.JobDetailsRecView
import com.example.wingstofly.adapters.SubjectsRecAdapter
import com.example.wingstofly.databinding.ChatBinding
import com.example.wingstofly.databinding.FragmentSharedBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationEndContainer


class SharedFragment : Fragment() {
    private lateinit var bind: ViewBinding
    private lateinit var scholar: Scholar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = arguments?.getParcelable<TransformationLayout.Params>("TransformationParams")
        onTransformationEndContainer(params)

        arguments?.let {
            scholar = it.getSerializable("scholar") as Scholar
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val scholarNumber = scholar.pfNumber
        if (scholarNumber!!.subSequence(0, 2) == "pf") {
            bind = FragmentSharedBinding.inflate(layoutInflater)
            bind.root.transitionName = "myTransitionName"

            (bind as FragmentSharedBinding).cutOff.text = "Scholar cut off: ${Constants.CUT_POINT}"

            (bind as FragmentSharedBinding).meanGrade.text = " Your mean grade is ${scholar.meanGrade}"
            if (Constants.CUT_POINT_OFF_POINTS < scholar.meanAGP) {
                (bind as FragmentSharedBinding).studentDeviation.text = "Congrats you are on the right track"
            } else if (Constants.CUT_POINT_OFF_POINTS == scholar.meanAGP) {
                (bind as FragmentSharedBinding).studentDeviation.text = "Hey, You are treading on a dangerous zone!!"
            } else {
                var deviation = scholar.calculateDeviation() * scholar.currentSubjects.size
                (bind as FragmentSharedBinding).studentDeviation.text =
                    "Add more than $deviation marks in each subject to be on the safe side."
            }
            val adp = SubjectsRecAdapter()
            adp.asyncList.submitList(scholar.currentSubjects)

            (bind as FragmentSharedBinding).subRecView.apply {
                adapter = adp
                layoutManager = LinearLayoutManager(requireContext())
            }
        }else{
            bind = ChatBinding.inflate(layoutInflater)
            val list = arrayListOf<String>("The Technical University of Kenya - Business Information Technology", "Moringa School - Software Development.")
            val list1 = arrayListOf<String>("Payments Officer - Equity Bank Ltd", "Casual Labourer - Osho.")
            val adp = JobDetailsRecView(list)
            val adp1 = JobDetailsRecView(list1)

            (bind as ChatBinding).recView.apply {
                adapter = adp
                layoutManager = LinearLayoutManager(requireContext())
            }

            (bind as ChatBinding).view.apply {
                adapter = adp1
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        return bind.root
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