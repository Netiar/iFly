package com.example.wingstofly.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.R
import com.example.wingstofly.adapters.JobsRecView
import kotlinx.android.synthetic.main.activity_jobs.*
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.activity_news.jobsRecView

class JobsFragment: Fragment(R.layout.activity_jobs) {
    private lateinit var jobsAdapter: JobsRecView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jobsAdapter = JobsRecView()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        jobsRecView.apply {
            adapter = jobsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}