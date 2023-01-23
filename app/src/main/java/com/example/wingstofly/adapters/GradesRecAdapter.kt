package com.example.wingstofly.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.databinding.FragmentGradesBinding

class GradesRecAdapter: RecyclerView.Adapter<GradesRecAdapter.MyHolder>() {
    inner class MyHolder(private val bind:FragmentGradesBinding): RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}