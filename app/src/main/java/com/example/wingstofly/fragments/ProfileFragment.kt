package com.example.wingstofly.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentProfileBinding
import com.example.wingstofly.models.Scholar
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment: Fragment(R.layout.fragment_profile), View.OnClickListener {
    private lateinit var param1: Scholar
    private lateinit var bind: FragmentProfileBinding
    private lateinit var fragmentScholarOverview: SingleScholarFragment
    private lateinit var fragmentshared: SharedFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable("scholar") as Scholar
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind = FragmentProfileBinding.inflate(layoutInflater)
        val scholar1 = param1

        scholar_name.text = param1.name
        scholar_status.text = "${param1.status!!.capitalize()}, ${param1.secondarySchool}"

        fragmentScholarOverview = SingleScholarFragment.newInstance(scholar1)
        fragmentshared = SharedFragment.newInstance(scholar1)

        replaceFragment(fragmentScholarOverview)

        overview.setOnClickListener(this::onClick)
        back.setOnClickListener(this::onClick)

        grades.setOnClickListener{
            replaceFragment(fragmentshared)
        }
        summary.setOnClickListener(this::onClick)
    }

    private fun replaceFragment(fragment: Fragment) {
        val manager = childFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(bind.frameLayout.id, fragment)
        transaction.commit()
    }

    override fun onClick(p0: View?) {
        if(p0 == grades){
            Toast.makeText(requireContext(), "yees", Toast.LENGTH_SHORT).show()
        }
        if(p0 == overview){
            replaceFragment(fragmentScholarOverview)
        }
        if (p0 == back){

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