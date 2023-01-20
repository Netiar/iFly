package com.example.wingstofly.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentEventsBinding
import kotlinx.android.synthetic.main.activity_event_recview.view.*

class NewsRecAdapter(var cont: Context): RecyclerView.Adapter<NewsRecAdapter.MyHolder>() {

    inner class MyHolder(view: View): RecyclerView.ViewHolder(view){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (parent.id == FragmentEventsBinding.inflate(LayoutInflater.from(cont)).eventsRecView.id ){
            MyHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.activity_event_recview,
                    parent,
                    false
                )

            )

        } else {
            MyHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.activity_news_recview,
                    parent,
                    false
                )

            )
        }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        if (holder.itemView.id == FragmentEventsBinding.inflate(LayoutInflater.from(cont)).eventsRecView.id ){
            holder.itemView.apply {
                newsDescription.text = cont.getString(R.string.events)
                newsId.text = cont.getString(R.string.eventId)
            }
        }
    }

    override fun getItemCount() = 10

}