package com.example.wingstofly.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.adapters.MessagesRecHolder
import com.example.wingstofly.databinding.MessagesBinding
import com.example.wingstofly.models.Message
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_single_job.view.*
import java.util.prefs.Preferences
import kotlin.apply

class MessagesFragment: Fragment(), View.OnClickListener {
    private lateinit var bind: MessagesBinding
    private var scholar: Scholar? = null
    private lateinit var pref: SharedPreferences
    private lateinit var databaseRef: DatabaseReference
    private lateinit var mAdapter: MessagesRecHolder
    private lateinit var messageList: ArrayList<Message>

    var senderRoom: String? = null
    var receiverRoom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            scholar = it.getSerializable("scholar") as Scholar?
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = MessagesBinding.inflate(layoutInflater)

        bind.jobTitle.text = scholar!!.name!!
        databaseRef = FirebaseDatabase.getInstance().reference
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        messageList = ArrayList()
        val userPf = pref.getString(Constants.PF_NUMBER, "")
        val receiverPf = scholar!!.pfNumber

        receiverRoom = userPf + receiverPf
        senderRoom = receiverPf + userPf

        databaseRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for (dataSnapshot in snapshot.children){
                        val message = dataSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    mAdapter = MessagesRecHolder(requireContext(),scholar!!,messageList )
                    bind.recMessages.apply{
                        adapter = mAdapter
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                    mAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        bind.send.setOnClickListener(this::onClick)

        return bind.root
    }

    private fun setUpRecyclerView() {

    }

    private fun createMessageObject() {
        val message = bind.yourMessage.text.toString()
        val userPf = pref.getString(Constants.PF_NUMBER, "")

        val messageObject = Message(userPf!!, message)

        //sending the message to the database
        databaseRef.child("chats").child(senderRoom!!).child("messages").push()
            .setValue(messageObject).addOnSuccessListener {
                databaseRef.child("chats").child(receiverRoom!!).child("messages").push()
                    .setValue(messageObject)
            }

    }


    companion object{
        fun newInstance(scholar: Scholar): MessagesFragment{
            val args = Bundle()
            args.apply {
                putSerializable("scholar", scholar)
            }

            val fragment = MessagesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.send){
            createMessageObject()
            bind.yourMessage.text = null
        }
    }


}