package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.R
import com.example.wingstofly.databinding.ScholarBinding
import com.example.wingstofly.fragments.ScholarFragment

class ScholarsRecAdapter: RecyclerView.Adapter<ScholarsRecAdapter.MyHolder>() {
    inner class MyHolder(private val bind: ScholarBinding): RecyclerView.ViewHolder(bind.root) {
        init {
            bind.root.setOnClickListener{
                it.findFragment<ScholarFragment>().findNavController().navigate(R.id.action_scholarFragment2_to_singleScholarFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = ScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

    }

    override fun getItemCount() = 15
}