package com.example.wingstofly.fragments

import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.adapters.BottomSheetRecAdapter
import com.example.wingstofly.adapters.ChatRecHolder
import com.example.wingstofly.adapters.ScholarsRecAdapter
import com.example.wingstofly.databinding.FragmentChatBinding
import com.example.wingstofly.models.Message
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.*
import kotlin.apply


class ChatFragment : Fragment(), View.OnClickListener {
    private lateinit var bind:FragmentChatBinding
    private lateinit var suggestionAdapter: ScholarsRecAdapter
    private lateinit var chatAdapter: ChatRecHolder
    private lateinit var bottomSheet: BottomSheetDialog
    private lateinit var scholarsList: ArrayList<Scholar>
    private lateinit var scholarsAdapter: BottomSheetRecAdapter
    private lateinit var pref: SharedPreferences
    private lateinit var mAuth: DatabaseReference

    private var realScholar: Scholar? = null
    private var pfNumber: String? = null
    private var hashMap = HashMap<Scholar, Message>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        bind = FragmentChatBinding.inflate(inflater)

        //initialising late init vars
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        scholarsList = (activity as MainActivity).scholars
        scholarsAdapter = BottomSheetRecAdapter(requireContext())
        scholarsAdapter.listDiffer.submitList(scholarsList)
        bottomSheet = BottomSheetDialog(requireContext())
        suggestionAdapter = ScholarsRecAdapter(requireContext())
        mAuth = FirebaseDatabase.getInstance().reference

        //getting the scholars pf number from shared preferences
        pfNumber = pref.getString(Constants.PF_NUMBER, null)

        val scholarSuggestion = ArrayList<Scholar>()

        var realScholar: Scholar? = null
        for (i in scholarsList.indices){
            if (pfNumber.contentEquals(scholarsList[i].pfNumber)){
                realScholar = scholarsList[i]
                for (i in scholarsList.indices){
                    if ((scholarsList[i].origin == realScholar.origin) || (scholarsList[i].secondarySchool == realScholar.secondarySchool)){
                        scholarSuggestion.add(scholarsList[i])
                    }
                }
            }

        }
        scholarSuggestion.remove(realScholar)

        //getting the scholars last message
        mAuth.child("chats").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children){
                    if(dataSnapshot.key!!.substring(0,7) == pfNumber ){
//                        Toast.makeText(requireContext(), dataSnapshot.key!!.substring(0,7), Toast.LENGTH_LONG).show()

                        mAuth.child("chats").child(dataSnapshot.key!!).child("messages").addValueEventListener(object: ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                bind.progress.isVisible = false
                                val scholarPf = dataSnapshot.key!!.substring(7)
                                val scholarMessages = ArrayList<Message>()
                                for (receiver in scholarsList){
                                    if(receiver.pfNumber == scholarPf){
                                        for (messageSnapshot in snapshot.children){
                                            scholarMessages.add(messageSnapshot.getValue(Message::class.java)!!)
                                        }
                                        hashMap[receiver] = scholarMessages[scholarMessages.size - 1]
                                        if (hashMap.size == 0){
                                            bind.chats.text = "You have no chats."
                                        }
                                        break
                                    }
                                }

                                chatAdapter.notifyDataSetChanged()


                            }

                            override fun onCancelled(error: DatabaseError) {
                                bind.progress.isVisible = true
                                bind.chats.text = "Check your internet connection "

                            }

                        })
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                bind.progress.isVisible = true
                bind.chats.text = "Check your internet connection "
            }

        } )

        chatAdapter = ChatRecHolder(hashMap, requireContext())
        bind.chatRecView.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        }


        //Creating the bottom sheet dialog
        createBottomSheetDialog(inflater, container)

        //adding onclick listeners
        bind.fab.setOnClickListener(this::onClick)





        suggestionAdapter.listDiffer.submitList(scholarSuggestion)
        bind.topRecView.apply {
            adapter = suggestionAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        }

        return bind.root
    }


    //method to create the bottom sheet dialog
    private fun createBottomSheetDialog(inflater: LayoutInflater, container: ViewGroup?) {
        val view = inflater.inflate(R.layout.bottom_sheet, null, false)

        //getting the recycler view in the bottom sheet dialog
        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = LinearLayoutManager(requireContext())
        recView.adapter = scholarsAdapter

        bottomSheet.setContentView(view)

        //bottom sheet behavior
        //1. Get the behavior from the coordinator layout
        val behavior = BottomSheetBehavior.from(view.parent as View)
        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        //2. Set the measurement metrics on the coordinator layout
        val layout = view.findViewById<ViewGroup>(R.id.coordinate)
        layout.minimumHeight = Resources.getSystem().displayMetrics.heightPixels
    }


    override fun onClick(p0: View?) {
        if (p0 == bind.fab){
            bottomSheet.show()
        }
    }

    //Dismissing the bottom sheet dialog when the fragment is stopped
    override fun onStop() {
        super.onStop()
        bottomSheet.dismiss()
    }

    override fun onResume() {
        super.onResume()
        val scholarSuggestion = ArrayList<Scholar>()

        var realScholar: Scholar? = null
        for (i in scholarsList.indices){
            if (pfNumber.contentEquals(scholarsList[i].pfNumber)){
                realScholar = scholarsList[i]
                for (i in scholarsList.indices){
                    if ((scholarsList[i].origin == realScholar.origin) || (scholarsList[i].secondarySchool == realScholar.secondarySchool)){
                        scholarSuggestion.add(scholarsList[i])
                    }
                }
            }

        }
        scholarSuggestion.remove(realScholar)
        suggestionAdapter.listDiffer.submitList(scholarSuggestion)
        bind.topRecView.apply {
            adapter = suggestionAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        }
    }

}