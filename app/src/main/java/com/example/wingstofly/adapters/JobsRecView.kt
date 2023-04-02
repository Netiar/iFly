package com.example.wingstofly.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
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
            bind.newsId.text = job.title
            if (job.description.isNullOrBlank()){
                bind.newsDescription.text = "Began working on ${job.postDate} to ${job.deadline}"
                bind.news.text = job.department
                bind.image.setImageResource(R.drawable.jumia)
                bind.topCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.maroon3))

            }else{
                bind.news.text = job.companyName
                bind.newsDescription.text = "${job.description.subSequence(0, 100)}....."
                bind.image.setImageResource(job.companyImage)
                bind.topCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.maroon2))

                bind.root.setOnClickListener{
                    val uri = Uri.parse(job.jobUrl)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    TransformationCompat.startActivity(bind.transformationLayout, intent)
                }
            }

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
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Job>(){
        override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
            return  oldItem.jobUrl == newItem.jobUrl
        }

        override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem.equals(newItem)
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