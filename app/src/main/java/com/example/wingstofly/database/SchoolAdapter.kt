package com.example.wingstofly.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.databinding.GraphBinding
import com.example.wingstofly.databinding.LearnBinding
import com.example.wingstofly.models.School

class SchoolAdapter(private val list: ArrayList<School>): RecyclerView.Adapter<SchoolAdapter.MyHolder>() {
    inner class MyHolder(val bind: GraphBinding): RecyclerView.ViewHolder(bind.root){
        fun setData(school: School){
           bind.name.text = school.name
            bind.level.text = "Course: ${school.course}"
            bind.startDate.text = "From: ${school.startYear}"
            bind.endDate.text = "To: ${school.endYear}"
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        GraphBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount() = list.size
}