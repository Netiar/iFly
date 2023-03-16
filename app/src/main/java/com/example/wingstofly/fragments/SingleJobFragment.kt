package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.adapters.JobDetailsRecView
import com.example.wingstofly.databinding.FragmentSingleJobBinding
import com.example.wingstofly.models.Job


class SingleJobFragment : Fragment() {
    private lateinit var job: Job
    private lateinit var bind:FragmentSingleJobBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            job = it?.getSerializable("job") as Job
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bind = FragmentSingleJobBinding.inflate(layoutInflater)

        val jobTasks  = job.jobAbout
        val jobRequirements  = job.requirements

        bind.scholarStatus.text = "Company name: ${job.companyName}"
        bind.jobTitle.text = job.title
        bind.jobLevel.text = job.jobLevel
        bind.jobDescription.text = job.description
        bind.image.setImageResource(job.companyImage)
        bind.postingDate.text = job.postDate.toString()
        bind.jobLevel.text = "Job Level: ${job.jobLevel}"
        bind.jobType.text = "Job Type: ${job.jobType}"

        bind.jobRequirements.apply {
            adapter = JobDetailsRecView(jobRequirements)
            layoutManager = LinearLayoutManager(requireContext())
        }

        bind.jobTasks.apply {
            adapter = JobDetailsRecView(jobTasks)
            layoutManager = LinearLayoutManager(requireContext())
        }
        bind.back.setOnClickListener{
            requireActivity().finish()
        }
        return bind.root
    }

    companion object{
        @JvmStatic
        fun newInstance(job: Job) =
            SingleJobFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("job", job)
                }
            }

    }

}