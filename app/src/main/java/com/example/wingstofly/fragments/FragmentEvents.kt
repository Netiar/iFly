package com.example.wingstofly.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.MainActivity
import com.example.wingstofly.adapters.NewsRecAdapter
import com.example.wingstofly.databinding.FragmentEventsBinding
import com.example.wingstofly.models.Event

class FragmentEvents: Fragment() {
    private  lateinit var bind: FragmentEventsBinding
    private lateinit var eventsAdapter: NewsRecAdapter
    private lateinit var events: ArrayList<Event>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       bind = FragmentEventsBinding.inflate(inflater)
        events = (activity as MainActivity).events

        eventsAdapter = NewsRecAdapter(requireContext())
        eventsAdapter.asyncList.submitList(events)
        setUpRecyclerView(bind.eventsRecView)

        return bind.root
    }
    private fun setUpRecyclerView(eventsRecView: RecyclerView) {
        eventsRecView.apply {
            adapter = eventsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}