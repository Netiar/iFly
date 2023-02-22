package com.example.wingstofly.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.R
import com.example.wingstofly.SingleActivity
import com.example.wingstofly.models.Event
import kotlinx.android.synthetic.main.activity_event_recview.view.*

class NewsRecAdapter(var cont: Context): RecyclerView.Adapter<NewsRecAdapter.MyHolder>() {

    inner class MyHolder(val view: View): RecyclerView.ViewHolder(view){

        fun setClickListener(event: Event){
            view.setOnClickListener{
                val intent = Intent(cont, SingleActivity::class.java)
                intent.putExtra("layout", 5)
                intent.putExtra("event", event )
                cont.startActivity(intent)
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
                R.layout.activity_event_recview,
                parent,
                false
            )

        )


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val event = asyncList.currentList[position]
        holder.itemView.apply {
            newsDescription.text = event.description!!.subSequence(0, 50)
            newsId.text = event.title
        }
        holder.setClickListener(event)
    }

    override fun getItemCount() = asyncList.currentList.size

}