package com.example.wingstofly.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.SingleActivity
import com.example.wingstofly.databinding.LearnBinding
import com.example.wingstofly.models.TriviaCategory
import com.skydoves.transformationlayout.TransformationCompat

class QuestRecAdapter(val context: Context): RecyclerView.Adapter<QuestRecAdapter.MyHolder>() {
    inner class MyHolder(val bind: LearnBinding): RecyclerView.ViewHolder(bind.root) {
        fun setData(cat: TriviaCategory){
            bind.name.text = cat.name
            bind.level.text = "Find questions on ${cat.name} to challenge yourself, choices are given."

            bind.root.setOnClickListener{
                val category = cat.id
                val catName = cat.name
                val intent = Intent(context, SingleActivity::class.java)
                intent.putExtra("category", category)
                intent.putExtra("catName", catName)
                intent.putExtra("layout", 8)
                TransformationCompat.startActivity(bind.transformationLayout, intent)
            }
        }

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
        val bind = LearnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(bind)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val topic = listDiffer.currentList[position]
        holder.setData(topic)
    }

    override fun getItemCount() = listDiffer.currentList.size
}