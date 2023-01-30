package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.databinding.ActivityGradesRecViewBinding
import com.example.wingstofly.models.Scholar

class GradesRecAdapter: RecyclerView.Adapter<GradesRecAdapter.MyHolder>() {
    inner class MyHolder(private val bind:ActivityGradesRecViewBinding): RecyclerView.ViewHolder(bind.root){
        fun setData(scholar: Scholar){
            bind.position.text = (asyncListDiffer.currentList.indexOf(scholar) + 1).toString()
            bind.scholarName.text = scholar.getName()
            bind.scholarGrade.text = scholar.meanGrade
            bind.scholarPoints.text = scholar.meanScore.toString()
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Scholar>(){
        override fun areItemsTheSame(oldItem: Scholar, newItem: Scholar): Boolean {
            return oldItem.pfNumber == newItem.pfNumber
        }

        override fun areContentsTheSame(oldItem: Scholar, newItem: Scholar): Boolean {
            return oldItem == newItem
        }

    }

    val asyncListDiffer = AsyncListDiffer(this, diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder{
        val view = ActivityGradesRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val scholar = asyncListDiffer.currentList[position]
        holder.setData(scholar)
    }

    override fun getItemCount() = asyncListDiffer.currentList.size
}