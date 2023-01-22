package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.MainActivity
import com.example.wingstofly.adapters.ChatRecHolder
import com.example.wingstofly.adapters.ScholarsRecAdapter
import com.example.wingstofly.databinding.FragmentChatBinding
import com.example.wingstofly.databinding.ScholarBinding
import com.example.wingstofly.models.Scholar


class ChatFragment : Fragment() {
    private lateinit var bind:FragmentChatBinding
    private lateinit var recBind: ScholarBinding
    private lateinit var suggestionAdapter: ScholarsRecAdapter
    private lateinit var chatAdapter: ChatRecHolder


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentChatBinding.inflate(inflater)
        recBind = ScholarBinding.inflate(inflater, container, false)

        suggestionAdapter = ScholarsRecAdapter(requireContext())
        val scholarsList = (activity as MainActivity).scholars

        val realScholar = Scholar("Charles Muvaka", "Student")
        realScholar.apply {
            setOrigin("Kericho Branch")
            primarySchool = "Umoja primary school"
            secondarySchool = "St Josephs Seminary Mwingi"
            varsity = "The Technical University of Kenya"
        }
        val scholarSuggestion = ArrayList<Scholar>()
        for (scholar in scholarsList){
            if (scholar.getOrigin() == realScholar.getOrigin()){
                scholarSuggestion.add(scholar)
            }
        }

        suggestionAdapter.listDiffer.submitList(scholarSuggestion)
        chatAdapter = ChatRecHolder()

        setUpRecyclerView()


        return bind.root
    }

    private fun setUpRecyclerView() {
        bind.topRecView.apply {
            adapter = suggestionAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        bind.chatRecView.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        }
    }


}