package com.example.wingstofly.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.SingleActivity
import com.example.wingstofly.database.DataScholarManager
import com.example.wingstofly.databinding.ActivityUpskillRecBinding
import com.example.wingstofly.models.Upskill

class UpskillRecHolder(private val context: Context): RecyclerView.Adapter<UpskillRecHolder.MyHolder>() {
    inner class MyHolder(val bind: ActivityUpskillRecBinding): RecyclerView.ViewHolder(bind.root){

        fun setData(upskill: Upskill){
            bind.activityName.text = upskill.title
            bind.activity.text = upskill.description!!.subSequence(0, 50)
            bind.activityPart.text = upskill.type

            bind.root.setOnClickListener{
                val bundle = Bundle()
                bundle.apply {
                    val scholar = DataScholarManager().scholars[0]
                    putSerializable("school", upskill)
                    putSerializable("scholar", scholar)
                    putInt("layoutNumber", 1)
                }
                val intent = Intent(context, SingleActivity::class.java)
                intent.putExtra("layout", 7)
                intent.putExtra("school", upskill)
                context.startActivity(intent)
//                bind.root.findFragment<JobsFragment>().findNavController().navigate(R.id.action_jobsFragment2_to_singleActivity2, bundle)
            }

        }

    }

    private val diffUtil = object : DiffUtil.ItemCallback<Upskill>(){
        override fun areItemsTheSame(oldItem: Upskill, newItem: Upskill): Boolean {
            return  oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Upskill, newItem: Upskill): Boolean {
            return  oldItem.equals(newItem)
        }

    }

    val asyncList = AsyncListDiffer(this, diffUtil)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
    ActivityUpskillRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(asyncList.currentList[position])
    }

    override fun getItemCount() = asyncList.currentList.size
}