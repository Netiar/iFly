package com.example.wingstofly.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.marginStart
import androidx.core.view.setMargins
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.wingstofly.R
import com.example.wingstofly.databinding.ActivityUpskillRecBinding
import com.example.wingstofly.databinding.GraphBinding
import com.example.wingstofly.models.Event

class NewsRecAdapter(var cont: Context): RecyclerView.Adapter<NewsRecAdapter.MyHolder>() {
    private var layoutType:Int? = null

    inner class MyHolder(val bind: ViewBinding): RecyclerView.ViewHolder(bind.root){
        //val layout: TransformationLayout = view.findViewById(R.id.transformationLayout)
        //val card: MaterialCardView = view.findViewById(R.id.topCard)
        fun setClickListener(event: Event){
            //card.setCardBackgroundColor(ContextCompat.getColor(cont, R.color.maroon2))
            bind.root.setOnClickListener{
                val uri = Uri.parse(event.eventUrl)
                val intent = Intent(Intent.ACTION_VIEW, uri)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if( layoutType == 0){
        MyHolder(ActivityUpskillRecBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    } else {
        MyHolder(GraphBinding.inflate(LayoutInflater.from(parent.context),parent, false))

    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        layoutType = position % 2
        val event = asyncList.currentList[position]
        if(layoutType == 0){
            val mainBind = holder.bind as GraphBinding
            mainBind.name.apply {
                textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                textSize = 20f
                text = event.title
            }
            mainBind.level.apply {
                animation = AnimationUtils.loadAnimation(cont, R.anim.zoom_in)
                text = "${event.description!!.subSequence(0, 100)}.....click to read more"
                setTextColor(ContextCompat.getColor(cont, R.color.black))
                setTextAppearance(android.R.style.TextAppearance_Medium)
                textSize = 20f

            }
            mainBind.topCard.setBackgroundColor(ContextCompat.getColor(cont,R.color.maroon3))
            mainBind.topCard.elevation = 12f
            mainBind.cont.setBackgroundColor(ContextCompat.getColor(cont,R.color.maroon2))
            mainBind.startDate.isVisible = false
            mainBind.endDate.isVisible = false


        }else{
            val mainBind = holder.bind as ActivityUpskillRecBinding
            mainBind.activityName.text = event.title
            mainBind.activity.apply {
                setTextColor(ContextCompat.getColor(cont, R.color.black))
                text = "${event.description!!.subSequence(0, 100)}.....click to read more"
                setTextAppearance(android.R.style.TextAppearance_Medium)
                textSize = 19f
            }
            mainBind.activityPart.isVisible = false
            mainBind.container.setBackgroundColor(ContextCompat.getColor(cont, R.color.maroon3))


        }
        holder.setClickListener(event)
    }

    override fun getItemCount() = asyncList.currentList.size

}