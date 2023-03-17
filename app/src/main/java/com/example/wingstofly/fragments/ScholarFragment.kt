package com.example.wingstofly.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.MainActivity
import com.example.wingstofly.adapters.ScholarsRecAdapter
import com.example.wingstofly.databinding.ChatBinding
import com.example.wingstofly.databinding.FragmentScholarBinding
import com.example.wingstofly.databinding.ScholarSuggestionBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants

class ScholarFragment : Fragment() {
    private lateinit var bind:FragmentScholarBinding
    private lateinit var suggestionsBind:ScholarSuggestionBinding
    private lateinit var scholarBind: ChatBinding
    private lateinit var navController: NavController
    private lateinit var pref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bind = FragmentScholarBinding.inflate(inflater)
        scholarBind = ChatBinding.inflate(inflater, container, false)
        suggestionsBind = ScholarSuggestionBinding.inflate(inflater, container, false)
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())

        //getting the scholars pf number
        val pfNumber = pref.getString(Constants.PF_NUMBER, null)
        val adapter = ScholarsRecAdapter(requireContext())
        val scholarsList = (activity as MainActivity).scholars


        adapter.listDiffer.submitList(scholarsList)

        val scholarSuggestion = ArrayList<Scholar>()

        var realScholar: Scholar? = null
        for (i in scholarsList.indices){
            if (pfNumber.contentEquals(scholarsList[i].pfNumber)){
                realScholar = scholarsList[i]
                for (i in scholarsList.indices){
                    if ((scholarsList[i].origin == realScholar.origin) || (scholarsList[i].secondarySchool == realScholar.secondarySchool)){
                        scholarSuggestion.add(scholarsList[i])
                    }
                }
            }

        }
        scholarSuggestion.remove(realScholar)

        val adapter1 = ScholarsRecAdapter(requireContext())
        adapter1.listDiffer.submitList(scholarSuggestion)
        bind.topRecView.apply {
            this.adapter = adapter1
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.hasFixedSize()
        }

        bind.myRecView.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(context)
        }
        return bind.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScholarFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}