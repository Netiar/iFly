package com.example.wingstofly.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.adapters.NewsRecAdapter
import com.example.wingstofly.databinding.FragmentEventsBinding
import com.example.wingstofly.models.Event
import com.example.wingstofly.utils.Constants

class FragmentEvents : Fragment() {
    private lateinit var bind: FragmentEventsBinding
    private lateinit var eventsAdapter: NewsRecAdapter
    private lateinit var events: ArrayList<Event>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentEventsBinding.inflate(inflater)
        events = (activity as MainActivity).events
        val scholars = (activity as MainActivity).scholars
        startAnimations()
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val scholarNumber = pref.getString(Constants.PF_NUMBER, null)

        //setting the scholar suggestion event
        var highSchoolEvent: Event? = null

        for (scholar in scholars) {
            if (scholar.pfNumber == scholarNumber) {
                val names = scholar.name!!.split(" ")
                bind.upcoming.text = "Hey, ${names[names.size - 1]}"
                for (event in events) {
                    if (scholarNumber!!.subSequence(0, 2) == "pf") {
                        if (event.id == 1) {
                            highSchoolEvent = event
                        }
                    }else{
                        if (event.id == 2){
                            highSchoolEvent = event
                        }
                    }
                }
            }
        }

        bind.topCard.setOnClickListener{
            val uri = Uri.parse(highSchoolEvent!!.eventUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            requireContext().startActivity(intent)
        }
        bind.activityName.text = highSchoolEvent!!.title
        bind.activity.text = "${highSchoolEvent.description!!.subSequence(0, 80)}..."
        bind.activityPart.text = highSchoolEvent.eventOwner

        eventsAdapter = NewsRecAdapter(requireContext())
        eventsAdapter.asyncList.submitList(events)
        setUpRecyclerView(bind.eventsRecView)

        return bind.root
    }

    private fun startAnimations() {
        bind.upcoming.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            )
        )
    }

    private fun setUpRecyclerView(eventsRecView: RecyclerView) {
        eventsRecView.apply {
            adapter = eventsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}