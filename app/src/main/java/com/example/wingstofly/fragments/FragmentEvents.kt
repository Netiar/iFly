package com.example.wingstofly.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.R
import com.example.wingstofly.adapters.NewsRecAdapter
import kotlinx.android.synthetic.main.fragment_events.*

class FragmentEvents: Fragment(R.layout.fragment_events) {
    private lateinit var eventsAdapter: NewsRecAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventsAdapter = NewsRecAdapter(requireContext())
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        eventsRecView.apply {
            adapter = eventsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}