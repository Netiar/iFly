package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.databinding.ActivityGradesRecViewBinding
import com.example.wingstofly.models.Subject

class SubjectsRecAdapter: RecyclerView.Adapter<SubjectsRecAdapter.MyHolder>() {
    inner class MyHolder(val bind: ActivityGradesRecViewBinding): RecyclerView.ViewHolder(bind.root){
        fun setData(subject: Subject){
            var index = (asyncList.currentList.indexOf(subject) + 1)
            bind.position.text = index.toString()
            bind.scholarName.text = subject.name
            bind.scholarGrade.text = subject.grade
            bind.scholarPoints.text = subject.agp.toString()
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Subject>(){
        override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem == newItem
        }

    }

    val asyncList = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = ActivityGradesRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(asyncList.currentList[position])
    }

    override fun getItemCount() = asyncList.currentList.size
}