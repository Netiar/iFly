package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentChatBinding
import com.example.wingstofly.databinding.FragmentScholarBinding
import kotlinx.android.synthetic.main.fragment_chat.view.top_rec_view
import kotlinx.android.synthetic.main.fragment_scholar.view.*

class ScholarsRecAdapter(): RecyclerView.Adapter<ScholarsRecAdapter.MyHolder>() {
    inner class MyHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view: View? = null
        if (parent.id == FragmentScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false).root.myRecView.id){
            view = LayoutInflater.from(parent.context).inflate(R.layout.scholar, parent,false)
        }else if(parent.id == FragmentScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false).root.top_rec_view.id){
            view = LayoutInflater.from(parent.context).inflate(R.layout.scholar_suggestion, parent,false)
        }else if(parent.id == FragmentChatBinding.inflate(LayoutInflater.from(parent.context), parent, false).root.top_rec_view.id){
            view = LayoutInflater.from(parent.context).inflate(R.layout.scholar_suggestion, parent,false)
        }else{
            view = LayoutInflater.from(parent.context).inflate(R.layout.scholar, parent,false)

        }
        return MyHolder(view!!)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

    }

    override fun getItemCount() = 15
}