package com.example.wingstofly.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.MainActivity
import com.example.wingstofly.adapters.JobsRecView
import com.example.wingstofly.databinding.ActivityJobsBinding
import com.example.wingstofly.models.Job

class JobsFragment: Fragment() {
    private lateinit var bind: ActivityJobsBinding
    private lateinit var jobsAdapter: JobsRecView
    private  var jobs = ArrayList<Job>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = ActivityJobsBinding.inflate(inflater)

        jobs = (activity as MainActivity).jobs

        jobsAdapter = JobsRecView(requireContext())
        jobsAdapter.asyncList.submitList(jobs)
        setUpRecyclerView()
        return bind.root
    }

    private fun setUpRecyclerView() {
        bind.jobsRecView.apply {
            adapter = jobsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}