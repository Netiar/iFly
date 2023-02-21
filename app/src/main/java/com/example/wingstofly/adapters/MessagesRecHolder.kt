package com.example.wingstofly.adapters

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.wingstofly.R
import com.example.wingstofly.models.Message
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants
import java.util.prefs.Preferences

class MessagesRecHolder(val context: Context, val scholar: Scholar, val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val RECEIVE_VIEW = 1
    val SENT_VIEW = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.received_message, parent, false)
            ReceiveHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.sent_message, parent, false)
            SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val userMessage = messageList[position]

        if (holder::class.java == SentViewHolder::class.java){
            val viewHolder = holder as SentViewHolder
            viewHolder.message.text = userMessage.description
        }else{
            val viewHolder = holder as ReceiveHolder
            viewHolder.message.text = userMessage.description

        }
    }

    override fun getItemViewType(position: Int): Int {
        val userMessage = messageList[position]
        val userPf = pref.getString(Constants.PF_NUMBER, null)
        return if (userPf == userMessage.userUid){
            SENT_VIEW
        }else{
            RECEIVE_VIEW
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val message: TextView = itemView.findViewById<TextView>(R.id.sent_message)
    }

    class ReceiveHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val message: TextView = itemView.findViewById<TextView>(R.id.received_message)
    }
}