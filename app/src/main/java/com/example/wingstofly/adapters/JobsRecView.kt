package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.R

class JobsRecView: RecyclerView.Adapter<JobsRecView.MyHolder>() {
    inner class MyHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.activity_job_recview,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
    }

    override fun getItemCount() = 10
}