package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.R

class ChatRecHolder: RecyclerView.Adapter<ChatRecHolder.MyHolder>() {
    inner class MyHolder(view: View): RecyclerView.ViewHolder(view){

    }

//    private var diffUtil:

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
    }

    override fun getItemCount() = 10

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.chat,
            parent,
            false
        )
    )
}