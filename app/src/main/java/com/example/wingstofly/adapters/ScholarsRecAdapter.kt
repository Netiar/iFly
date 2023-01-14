package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.wingstofly.R
import com.example.wingstofly.databinding.ChatBinding
import com.example.wingstofly.databinding.FragmentScholarBinding
import com.example.wingstofly.databinding.ScholarBinding
import com.example.wingstofly.databinding.ScholarSuggestionBinding
import com.example.wingstofly.fragments.ScholarFragment

class ScholarsRecAdapter(private var recBind: ViewBinding?, private var mainBind:FragmentScholarBinding): RecyclerView.Adapter<ScholarsRecAdapter.MyHolder>() {
    inner class MyHolder(bind: ViewBinding): RecyclerView.ViewHolder(bind.root) {
        init {
            bind.root.setOnClickListener{
                it.findFragment<ScholarFragment>().findNavController().navigate(R.id.action_scholarFragment2_to_singleScholarFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        recBind = if (parent == mainBind.myRecView){
            Toast.makeText(parent.context, "${parent.toString()}", Toast.LENGTH_LONG).show()
            ScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else{
            Toast.makeText(parent.context,
                parent.toString(), Toast.LENGTH_LONG).show()
            ScholarSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
        return MyHolder(recBind!!)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

    }

    override fun getItemCount() = 15
}