package com.example.wingstofly.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.R
import com.example.wingstofly.SingleActivity
import com.example.wingstofly.database.DataScholarManager
import com.example.wingstofly.databinding.ActivityJobRecviewBinding
import com.example.wingstofly.models.Job
import com.skydoves.transformationlayout.TransformationCompat

class JobsRecView(private val context: Context): RecyclerView.Adapter<JobsRecView.MyHolder>() {
    inner class MyHolder(val bind: ActivityJobRecviewBinding): RecyclerView.ViewHolder(bind.root){
        fun setData(job: Job){
            bind.newsId.text = "${job.title}(${job.companyName})"
            bind.newsDescription.text = "${job.description.subSequence(0, 150)}....."
            bind.image.setImageResource(job.companyImage)

            bind.newsId.startAnimation(
                AnimationUtils.loadAnimation(
                context, R.anim.zoom_in
            ))

            bind.newsDescription.startAnimation(AnimationUtils.loadAnimation(
                context, R.anim.zoom_in
            ))
            bind.image.startAnimation(AnimationUtils.loadAnimation(
                context, R.anim.zoom_in
            ))

            bind.root.setOnClickListener{
                val bundle = Bundle()
                bundle.apply {
                    val scholar = DataScholarManager().scholars[0]
                    putSerializable("job", job)
                    putSerializable("scholar", scholar)
                    putInt("layoutNumber", 1)
                }
                val intent = Intent(context, SingleActivity::class.java)
                intent.putExtra("layout", 1)
                intent.putExtra("job", job)
                TransformationCompat.startActivity(bind.transformationLayout, intent)
//                bind.root.findFragment<JobsFragment>().findNavController().navigate(R.id.action_jobsFragment2_to_singleActivity2, bundle)
            }
        }


    }

    private val diffUtil = object : DiffUtil.ItemCallback<Job>(){
        override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
            return  oldItem.jobUrl == newItem.jobUrl
        }

        override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
            return  oldItem == newItem
        }

    }

    val asyncList = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        ActivityJobRecviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(asyncList.currentList[position])
    }

    override fun getItemCount() = asyncList.currentList.size
}