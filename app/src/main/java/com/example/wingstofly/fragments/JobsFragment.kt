package com.example.wingstofly.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.MainActivity
import com.example.wingstofly.adapters.JobsRecView
import com.example.wingstofly.databinding.ActivityJobsBinding
import com.example.wingstofly.models.Job
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants

class JobsFragment: Fragment() {
    private lateinit var bind: ActivityJobsBinding
    private lateinit var jobsAdapter: JobsRecView
    private lateinit var preferences: SharedPreferences

    private  var jobs = ArrayList<Job>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = ActivityJobsBinding.inflate(inflater)
        preferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        jobs = (activity as MainActivity).jobs
        //start Animations
        val scholarNumber = preferences.getString(Constants.PF_NUMBER, null)

        val scholars = (activity as MainActivity).scholars
        var currentScholar: Scholar? = null
        for (scholar in scholars){
            if (scholar.pfNumber == scholarNumber){
                currentScholar = scholar
            }
        }

        if (currentScholar!!.skillSet != null){
            val skillSet = currentScholar.skillSet
            val list = ArrayList<Job>()
            for (job in jobs){
                if (job.skillSet == skillSet){
                    bind.activity.text = "You, a ${job.skillSet} is required to be a ${job.title}"
                    bind.activityName.text = job.companyName
                    bind.activityPart.text = job.jobLevel
                }
            }

        }else{
            var job: Job? = null
            for (job1 in jobs){
                if (job1.skillSet == "Relationship Officer"){
                    job = job1
                }
            }
            bind.activityName.text = job!!.companyName
            bind.activity.text = "You, a ${job.skillSet} is required to be a ${job.title}"
            bind.activityPart.text = job.jobLevel
        }

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