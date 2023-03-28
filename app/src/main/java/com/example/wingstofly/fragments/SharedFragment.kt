package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.wingstofly.adapters.SubjectsRecAdapter
import com.example.wingstofly.databinding.FragmentSharedBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants
import kotlin.properties.Delegates


class SharedFragment : Fragment() {
    private lateinit var bind: ViewBinding
    private lateinit var scholar: Scholar
    private var number: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            scholar = it.getSerializable("scholar") as Scholar
            number = it.getInt("number")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentSharedBinding.inflate(layoutInflater)
        bind.root.transitionName = "myTransitionName"
        (bind as FragmentSharedBinding).currentGrades.text = "Form ${number + 1} grades"

        (bind as FragmentSharedBinding).cutOff.text = "Scholar cut off: ${Constants.CUT_POINT}"

        (bind as FragmentSharedBinding).meanGrade.text =
            "Form ${number + 1} mean grade: ${scholar.meanGrade[number.toString()]}"
        if (scholar.pfNumber!!.subSequence(0, 2) == "pf") {
            if (Constants.CUT_POINT_OFF_POINTS < scholar.meanAGP[number.toString()]!!) {
                (bind as FragmentSharedBinding).studentDeviation.text =
                    "Congrats you are on the right track"
            } else if (Constants.CUT_POINT_OFF_POINTS == scholar.meanAGP[number.toString()]) {
                (bind as FragmentSharedBinding).studentDeviation.text =
                    "Hey, You are treading on a dangerous zone!!"
            } else {
                var deviation =
                    scholar.calculateDeviation(scholar.meanAGP[number.toString()]!!) * scholar.formGrades[number.toString()]!!.size
                (bind as FragmentSharedBinding).studentDeviation.text =
                    "$deviation marks required in each subject to beat the cut off."
            }
        } else {
            (bind as FragmentSharedBinding).studentDeviation.text =
                "Congrats you are on the right track"
        }
        val adp = SubjectsRecAdapter()
        adp.asyncList.submitList(scholar.formGrades[number.toString()])

        (bind as FragmentSharedBinding).subRecView.apply {
            adapter = adp
            layoutManager = LinearLayoutManager(requireContext())
        }



        return bind.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Scholar, i: Int) =
            SharedFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("scholar", param1)
                    putInt("number", i)
                }
            }
    }

}