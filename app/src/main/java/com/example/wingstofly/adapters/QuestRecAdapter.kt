package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.R
import com.example.wingstofly.databinding.LearnBinding
import com.example.wingstofly.models.ResultX
import kotlinx.android.synthetic.main.learn.view.*

class QuestRecAdapter: RecyclerView.Adapter<QuestRecAdapter.MyHolder>() {
    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    private val diffUtil = object : DiffUtil.ItemCallback<ResultX>(){
        override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem.question == newItem.question
        }

        override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem == newItem
        }

    }

    val listDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.learn, parent, false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val topic = listDiffer.currentList[position]
        holder.itemView.apply {
            name.text = topic.category
            level.text = topic.question
        }
    }

    override fun getItemCount() = listDiffer.currentList.size
}