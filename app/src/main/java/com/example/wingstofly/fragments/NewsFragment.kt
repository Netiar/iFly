package com.example.wingstofly.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wingstofly.adapters.NewsRecAdapter
import com.example.wingstofly.databinding.ActivityNewsBinding
import com.example.wingstofly.models.Event

class NewsFragment: Fragment(), View.OnClickListener {
    private lateinit var bind: ActivityNewsBinding
    private lateinit var newsAdapter: NewsRecAdapter
    private var event: Event? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            event = it.getSerializable("event") as Event?
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = ActivityNewsBinding.inflate(inflater)

        //binding event data to the fragment
        bind.jobTitle.text = event!!.title
        bind.scholarStatus.text = event!!.eventOwner
        bind.description.text = event!!.description

        bind.postingDate.text = event!!.date.toString()
        bind.jobType.text = event!!.venue

        bind.back.setOnClickListener(this::onClick)

        return bind.root
    }

    companion object{
        fun newInstance(event: Event): NewsFragment{
            val args = Bundle().apply {
                putSerializable("event", event)
            }
            val fragment = NewsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.back){
            requireActivity().finish()
        }
    }
}