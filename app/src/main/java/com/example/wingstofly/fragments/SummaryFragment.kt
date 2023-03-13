package com.example.wingstofly.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wingstofly.R
import com.example.wingstofly.databinding.GraphBinding
import com.example.wingstofly.models.Scholar
import kotlin.apply

class SummaryFragment: Fragment() {
    private lateinit var bind: GraphBinding
    private lateinit var scholar: Scholar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            scholar = it.getSerializable("scholar") as Scholar
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = GraphBinding.inflate(layoutInflater)
//        val series = LineGraphSeries(arrayOf(
//            DataPoint(0.1),
//            DataPoint(0,2)
//        ))



        return bind.root
    }

    companion object{
        fun newInstance(scholar: Scholar): SummaryFragment{
            val args = Bundle()
            args.apply {
                putSerializable("scholar", scholar)
            }
            val fragment = SummaryFragment()
            fragment.arguments = args
            return fragment
        }
    }
}