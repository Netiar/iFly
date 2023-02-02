package com.example.wingstofly.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.fragment.navArgs
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentProfileBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment: Fragment(R.layout.fragment_profile), View.OnClickListener {
    private lateinit var param1: Scholar
    val args: ProfileFragmentArgs by navArgs()
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
        scholar_status.text = "${param1.status.capitalize()}, ${param1.secondarySchool}"

        fragmentScholarOverview = SingleScholarFragment.newInstance(scholar1)
        fragmentshared = SharedFragment.newInstance(scholar1)

        replaceFragment(fragmentshared)

        bind.overview.setOnClickListener(this::onClick)
        bind.grades.setOnClickListener(this::onClick)
        bind.summary.setOnClickListener(this::onClick)
    }

    private fun replaceFragment(fragment: Fragment) {
        val manager = childFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(bind.frameLayout.id, fragment)
        transaction.commit()
    }

    override fun onClick(p0: View?) {
        if(p0 == bind.grades){
            replaceFragment(fragmentScholarOverview)
        }
        if(p0 == bind.overview){
            replaceFragment(fragmentshared)
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