package com.example.wingstofly.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.SingleActivity
import com.example.wingstofly.databinding.ChatBinding
import com.example.wingstofly.models.Message
import com.example.wingstofly.models.Scholar

class ChatRecHolder(
    private val messages: HashMap<Scholar, Message>, private val cont: Context
): RecyclerView.Adapter<ChatRecHolder.MyHolder>() {
    inner class MyHolder(val bind: ChatBinding): RecyclerView.ViewHolder(bind.root){

        fun setData(scholar: Scholar){
            bind.scholarName.text = scholar.name
            if (messages[scholar]!!.description!!.length > 20){
                bind.scholarStatus.text = "${messages[scholar]!!.description!!.subSequence(0,20)}..."
            }else{
                bind.scholarStatus.text = messages[scholar]!!.description
            }

            bind.root.setOnClickListener{
                val intent = Intent(cont, SingleActivity::class.java)
                intent.putExtra("layout", 3)
                intent.putExtra("scholar", scholar)
                cont.startActivity(intent)
            }
        }
    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(messages.keys.elementAt(position))
    }

    override fun getItemCount() = messages.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        ChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}