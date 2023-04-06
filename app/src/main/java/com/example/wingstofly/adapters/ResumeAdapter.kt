package com.example.wingstofly.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.databinding.ActivityEventRecviewBinding
import com.example.wingstofly.models.Job
import com.example.wingstofly.models.School

class ResumeAdapter(private val cont: Context) : RecyclerView.Adapter<ResumeAdapter.MyHolder>() {
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

        val jobDesc = arrayListOf(
            "What role did you do",
            "More opportunities for you"
        )
        if (list.isNullOrEmpty()) {
            val jobsResumeAdapter = JobDetailsRecView(jobDesc)

            if(placeUsed == "Landscape"){
                jobsResumeAdapter.placeUsed = "Landscape"
                holder.bind.newsId.text = list1!![position].title
                holder.bind.newsId.textSize = 20f
                holder.bind.newsDescription.text = list1!![position].department
                holder.bind.newsDescription.textSize = 18f
                holder.bind.dates.text =
                "From ${list1!![position].deadline} to ${list1!![position].postDate}"
                holder.bind.dates.textSize = 15f

                holder.bind.details.apply {
                    adapter = jobsResumeAdapter
                    layoutManager = LinearLayoutManager(cont)
                }
            }else{
                jobsResumeAdapter.placeUsed = "Resume"
                holder.bind.newsId.text = list1!![position].title
                holder.bind.newsDescription.text = list1!![position].department
                holder.bind.dates.text =
                    "From ${list1!![position].deadline} to ${list1!![position].postDate}"
                holder.bind.details.apply {
                    adapter = jobsResumeAdapter
                    layoutManager = LinearLayoutManager(cont)
                }
            }
        } else {
            val jobDescriptions = arrayListOf(
                "What did you learn",
                "Tell us about your skills"
            )
            val jobsResumeAdapter = JobDetailsRecView(jobDescriptions)
            jobsResumeAdapter.placeUsed = "Resume"
            holder.bind.newsId.text = list!![position].name


            if (placeUsed == "Resume") {
                holder.bind.newsDescription.text = list!![position].course
                holder.bind.dates.apply {
                    textSize = 10f
                    text = "From - ${list!![position].startYear} to ${list!![position].endYear}"
                }
                holder.bind.details.apply {
                    adapter = jobsResumeAdapter
                    layoutManager = LinearLayoutManager(cont)
                }
            } else if (placeUsed == "Landscape") {

                holder.bind.newsId.apply {
                    textSize = 20f
                    text = list!![position].name
                }

                holder.bind.newsDescription.apply {
                    textSize = 18f
                    text = list!![position].course
                }
                holder.bind.dates.apply {
                    textSize = 15f
                    text = "From - ${list!![position].startYear} to ${list!![position].endYear}"
                }

                val jobsResumeAdapter = JobDetailsRecView(jobDescriptions)
                jobsResumeAdapter.placeUsed = "Landscape"
                holder.bind.newsId.text = list!![position].name

                holder.bind.details.apply {
                    adapter = jobsResumeAdapter
                    layoutManager = LinearLayoutManager(cont)
                }

            }else if(placeUsed == "referees") {
                holder.bind.newsId.
                    text = "${list!!.indexOf(list!![position]) + 1}. ${list!![position].name}"

                holder.bind.newsDescription.
                    text = list!![position].course

                holder.bind.dates.
                    text = "${list!![position].endYear} \n ${list!![position].startYear}"

            }
            else {
                holder.bind.newsId.apply {
                    textSize = 20f
                    text = "${list!!.indexOf(list!![position]) + 1}. ${list!![position].name}"

                }

                holder.bind.newsDescription.apply {
                    textSize = 18f
                    text = list!![position].course
                }
                holder.bind.dates.apply {
                    textSize = 15f
                    text = "${list!![position].endYear} \n ${list!![position].startYear}"
                }
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