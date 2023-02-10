package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
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

        bind.jobTasks.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, jobTasks )

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