package com.example.wingstofly.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.R
import com.example.wingstofly.databinding.ActivityEventRecviewBinding
import com.example.wingstofly.models.Job
import com.example.wingstofly.models.School

class ResumeAdapter(val cont: Context) : RecyclerView.Adapter<ResumeAdapter.MyHolder>() {
    var list: ArrayList<School>? = null
    var list1: ArrayList<Job>? = null
    var placeUsed: String? = null

    inner class MyHolder(val bind: ActivityEventRecviewBinding) :
        RecyclerView.ViewHolder(bind.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        ActivityEventRecviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        if (list.isNullOrEmpty()) {
            val jobDescriptions = arrayListOf(
                "What role did you do",
                "More opportunities for you"
            )
            val jobsResumeAdapter = JobDetailsRecView(jobDescriptions)
            jobsResumeAdapter.placeUsed = "Resume"
            holder.bind.newsId.text = list1!![position].title
            holder.bind.newsDescription.text = list1!![position].department
            holder.bind.dates.text =
                "From ${list1!![position].deadline} to ${list1!![position].postDate}"
            holder.bind.details.apply {
                adapter = jobsResumeAdapter
                layoutManager = LinearLayoutManager(cont)
            }
        } else {
            val jobDescriptions = arrayListOf(
                "What did you learn",
                "Tell us about your skills"
            )
            val jobsResumeAdapter = JobDetailsRecView(jobDescriptions)
            jobsResumeAdapter.placeUsed = "Resume"
            holder.bind.newsId.text = list!![position].name


            if (placeUsed.isNullOrBlank()) {
                holder.bind.dates.apply {
                    textSize = 10f
                    text = "From - ${list!![position].startYear} to ${list!![position].endYear}"
                }
                holder.bind.details.apply {
                    adapter = jobsResumeAdapter
                    layoutManager = LinearLayoutManager(cont)
                }
            } else {
                holder.bind.dates.apply {
                    textSize = 12f
                    text = "${list!![position].endYear} \n ${list!![position].startYear}"
                }
            }
            if (placeUsed == "Landscape") {

                holder.bind.newsId.apply {
                    textSize = 20f
                    text = list!![position].name
                }

                holder.bind.newsDescription.apply {
                    textSize = 18f
                    text = list!![position].course
                }
                holder.bind.dates.apply {
                    textSize = 17f
                    text = "From - ${list!![position].startYear} to ${list!![position].endYear}"
                }

            } else {
                holder.bind.newsDescription.text = list!![position].course
            }

        }
    }

    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()) {
            list1!!.size
        } else {
            list!!.size
        }
    }
}