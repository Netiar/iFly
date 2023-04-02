package com.example.wingstofly.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.R
import com.example.wingstofly.SingleActivity
import com.example.wingstofly.models.Event
import com.google.android.material.card.MaterialCardView
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import kotlinx.android.synthetic.main.activity_event_recview.view.newsDescription
import kotlinx.android.synthetic.main.activity_event_recview.view.newsId
import kotlinx.android.synthetic.main.activity_job_recview.view.*

class NewsRecAdapter(var cont: Context): RecyclerView.Adapter<NewsRecAdapter.MyHolder>() {

    inner class MyHolder(val view: View): RecyclerView.ViewHolder(view){
        val layout: TransformationLayout = view.findViewById(R.id.transformationLayout)
        val card: MaterialCardView = view.findViewById(R.id.topCard)
        fun setClickListener(event: Event){
            card.setCardBackgroundColor(ContextCompat.getColor(cont, R.color.maroon2))
            view.setOnClickListener{
                val uri = Uri.parse(event.eventUrl)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                TransformationCompat.startActivity(layout, intent)
            }
        }
    }

    private val diffUtil = object: DiffUtil.ItemCallback<Event>() {

        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.equals(newItem)
        }

    }

    val asyncList = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_job_recview,
                parent,
                false
            )

        )


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val event = asyncList.currentList[position]
        holder.itemView.apply {
            newsDescription.text = "${event.description!!.subSequence(0, 100)}.....click to read more"
            newsId.text = event.title
            news.isVisible = false
            image.isVisible = false

            newsDescription.startAnimation(
                AnimationUtils.loadAnimation(
                context, R.anim.zoom_in
            ))
            newsId.startAnimation(AnimationUtils.loadAnimation(
                context, R.anim.zoom_in
            ))

        }
        holder.setClickListener(event)
    }

    override fun getItemCount() = asyncList.currentList.size

}