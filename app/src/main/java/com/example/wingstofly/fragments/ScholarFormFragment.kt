package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.wingstofly.adapters.ViewPagerAdapter
import com.example.wingstofly.databinding.FragmentScholarFormBinding
import com.example.wingstofly.models.Scholar

class ScholarFormFragment : Fragment() {
    private lateinit var scholar: Scholar
    private lateinit var bind: ViewBinding
    private var fragmentsName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            scholar = it.getSerializable("scholar") as Scholar
            fragmentsName = it.getString("fragments")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val scholarNumber = scholar.pfNumber
        if(fragmentsName == "grades"){
            bind = FragmentScholarFormBinding.inflate(inflater, container, false)
            val fragmentList = ArrayList<Fragment>()
            for (i in 1 until 5 ){
                val fragment = GradesFragment.newInstance(i, scholarNumber!!)
                fragmentList.add(fragment)
            }
            val pagerAdapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
            (bind as FragmentScholarFormBinding).viewPager.adapter = pagerAdapter

        }else{
            if (scholarNumber!!.subSequence(0, 2) == "pf" || scholar.scholarPfTest == "pfsn"){
                bind = FragmentScholarFormBinding.inflate(inflater, container, false)
                val fragmentList = ArrayList<Fragment>()

                for (i in 0 until scholar.form){
                    val fragment = SharedFragment.newInstance(scholar, i)
                    fragmentList.add(fragment)
                }

                val pagerAdapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
                (bind as FragmentScholarFormBinding).viewPager.adapter = pagerAdapter

            }else {
                            }

        }
        return bind.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Scholar, fragmentsName: String) =
            ScholarFormFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("scholar", param1)
                    putString("fragments", fragmentsName)
                }
            }
    }
}