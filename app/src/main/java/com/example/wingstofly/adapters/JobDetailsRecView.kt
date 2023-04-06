package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.databinding.ActivityJobDetailsRecViewBinding

class JobDetailsRecView(val list: ArrayList<String>): RecyclerView.Adapter<JobDetailsRecView.MyHolder>() {
    var placeUsed: String? = null
    inner class MyHolder(private val bind:ActivityJobDetailsRecViewBinding): RecyclerView.ViewHolder(bind.root){
            fun setData(item: String){
                if (placeUsed.isNullOrBlank()){
                    bind.description.text = "${list.indexOf(item) + 1} - $item"
                }else if (placeUsed == "Resume"){
                    bind.description.apply {
                        textSize = 12f
                        text = "${list.indexOf(item) + 1} - $item"
                    }
                }else{
                    bind.description.apply {
                        textSize = 18f
                        text = "${list.indexOf(item) + 1} - $item"
                    }
                }
            }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        ActivityJobDetailsRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(list[position])

    }

    override fun getItemCount() = list.size
}