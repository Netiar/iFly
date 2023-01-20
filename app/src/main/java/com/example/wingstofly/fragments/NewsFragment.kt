package com.example.wingstofly.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.R
import com.example.wingstofly.adapters.NewsRecAdapter
import kotlinx.android.synthetic.main.activity_news.*

class NewsFragment: Fragment(R.layout.activity_news) {
    private lateinit var newsAdapter: NewsRecAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsRecAdapter(requireContext())
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        jobsRecView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}