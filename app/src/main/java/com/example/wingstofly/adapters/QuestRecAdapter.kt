package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.R
import com.example.wingstofly.models.TriviaCategory
import kotlinx.android.synthetic.main.learn.view.*

class QuestRecAdapter: RecyclerView.Adapter<QuestRecAdapter.MyHolder>() {
    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    private val diffUtil = object : DiffUtil.ItemCallback<TriviaCategory>(){
        override fun areItemsTheSame(oldItem: TriviaCategory, newItem: TriviaCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TriviaCategory, newItem: TriviaCategory): Boolean {
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
            name.text = topic.name
            level.text = topic.id.toString()
        }
    }

    override fun getItemCount() = listDiffer.currentList.size
}