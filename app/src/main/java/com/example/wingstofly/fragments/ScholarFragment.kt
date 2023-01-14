package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.wingstofly.R
import com.example.wingstofly.adapters.ScholarsRecAdapter
import com.example.wingstofly.databinding.ChatBinding
import com.example.wingstofly.databinding.FragmentScholarBinding
import com.example.wingstofly.databinding.ScholarBinding
import com.example.wingstofly.databinding.ScholarSuggestionBinding

class ScholarFragment : Fragment() {
    private lateinit var bind:FragmentScholarBinding
    private var suggestionsbind:ScholarSuggestionBinding? = null
    private var scholarbind: ChatBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentScholarBinding.inflate(inflater)

//        scholarbind = ChatBinding.inflate(inflater, container, false)
//        suggestionsbind = ScholarSuggestionBinding.inflate(inflater, container, false)

        val adapter = ScholarsRecAdapter(scholarbind, bind)
        val adapter1 = ScholarsRecAdapter(suggestionsbind, bind)
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