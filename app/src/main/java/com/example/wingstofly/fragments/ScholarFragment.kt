package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.adapters.ScholarsRecAdapter
import com.example.wingstofly.database.DataScholarManager
import com.example.wingstofly.databinding.ChatBinding
import com.example.wingstofly.databinding.FragmentChatBinding
import com.example.wingstofly.databinding.FragmentScholarBinding
import com.example.wingstofly.databinding.ScholarBinding
import com.example.wingstofly.databinding.ScholarSuggestionBinding
import com.example.wingstofly.models.Scholar

class ScholarFragment : Fragment() {
    private lateinit var bind:FragmentScholarBinding
    private lateinit var suggestionsBind:ScholarSuggestionBinding
    private lateinit var scholarBind: ChatBinding
    private lateinit var navController: NavController

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

        val adapter = ScholarsRecAdapter(requireContext())
        val scholarsList = (activity as MainActivity).scholars


        adapter.listDiffer.submitList(scholarsList)

        val scholarSuggestion = (activity as MainActivity).scholarSuggestion

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