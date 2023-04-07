package com.example.wingstofly.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentProfileBinding
import com.example.wingstofly.models.Scholar
import com.skydoves.transformationlayout.onTransformationStartContainer

class ProfileFragment: Fragment(R.layout.fragment_profile), View.OnClickListener {
    private lateinit var param1: Scholar
    private lateinit var bind: FragmentProfileBinding
    private lateinit var fragmentScholarOverview: SingleScholarFragment
    private lateinit var fragmentshared: ScholarFormFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onTransformationStartContainer()
        arguments?.let {
            param1 = it.getSerializable("scholar") as Scholar
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        bind = FragmentProfileBinding.inflate(layoutInflater)
        val scholar1 = param1

        fragmentScholarOverview = SingleScholarFragment.newInstance(scholar1)

        fragmentshared = ScholarFormFragment.newInstance(scholar1, "notGrades")


        bind.scholarName.text = param1.name
        if (scholar1.pfNumber!!.subSequence(0, 2) == "pf") {
            bind.scholarStatus.text = "Form ${param1.form}, ${param1.secondarySchool}"
            bind.summary.isEnabled = false

        } else {
            bind.summary.text = "employment"

            bind.summary.setOnClickListener {
                val fragment = GradesFragment.newInstance(7, param1.pfNumber!!)
                replaceFragment(fragment)
            }

            if (scholar1.status == "Employed") {
                if (scholar1.workPlaces.isNotEmpty()) {
                    bind.scholarStatus.text =
                        "${param1.workPlaces[0].title} at ${param1.workPlaces[0].department}"
                } else {
                    bind.scholarStatus.text = "${scholar1.status} at Not Updated"

                }
            } else if (scholar1.status == "Student") {
                bind.scholarStatus.text = "${param1.status}, ${param1.varsity}"
            } else {
                bind.scholarStatus.text = "${param1.status}"
            }
        }

        replaceFragment(fragmentScholarOverview)

        bind.overview.setOnClickListener(this::onClick)
        bind.back.setOnClickListener(this::onClick)

        bind.grades.setOnClickListener {
            val manager = childFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(bind.frameLayout.id, fragmentshared)
            transaction.commit()

        }
        return bind.root
    }


    private fun replaceFragment(fragment: Fragment) {
        val manager = childFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(bind.frameLayout.id, fragment)
        transaction.commit()
    }

    override fun onClick(p0: View?) {
        if(p0 == bind.grades){
            Toast.makeText(requireContext(), "yees", Toast.LENGTH_SHORT).show()
        }
        if(p0 == bind.overview){
            replaceFragment(fragmentScholarOverview)
        }
        if (p0 == bind.back){
            requireActivity().finish()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Scholar) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("scholar", param1)
                }
            }
    }


}