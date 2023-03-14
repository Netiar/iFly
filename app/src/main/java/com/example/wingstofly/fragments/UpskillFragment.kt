package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentUpskillBinding
import com.example.wingstofly.models.Upskill

class UpskillFragment : Fragment() {
    private lateinit var bind: FragmentUpskillBinding
    private var param1: Upskill? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable("school") as Upskill
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentUpskillBinding.inflate(layoutInflater)

        bind.jobTitle.text = param1!!.title
        bind.scholarStatus.text = param1!!.type
        bind.description.text = param1!!.description
        bind.postingDate.text = param1!!.type
        bind.jobType.isVisible = false

        bind.back.setOnClickListener{
            requireActivity().finish()
        }
        return bind.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Upskill) =
            UpskillFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("school", param1)
                }
            }
    }
}