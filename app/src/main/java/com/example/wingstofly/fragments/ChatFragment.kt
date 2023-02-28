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
    private lateinit var databaseReference: DatabaseReference

    private var realScholar: Scholar? = null
    private var pfNumber: String? = null
    lateinit var hashMap: HashMap<Scholar, Message>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bind = FragmentChatBinding.inflate(inflater)
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        databaseReference = FirebaseDatabase.getInstance().reference
        scholarsList = (activity as MainActivity).scholars
        scholarsAdapter = BottomSheetRecAdapter(requireContext())
        scholarsAdapter.listDiffer.submitList(scholarsList)



        bottomSheet = BottomSheetDialog(requireContext())
        createBottomSheetDialog(inflater, container)

        suggestionAdapter = ScholarsRecAdapter(requireContext())

        bind.fab.setOnClickListener(this::onClick)

        pfNumber = pref.getString(Constants.PF_NUMBER, null)
        getScholar(pfNumber)
        hashMap = (activity as MainActivity).hashMap

        if (hashMap.size != 0){
            chatAdapter = (activity as MainActivity).chatAdapter
            setUpRecyclerView()

        }else{
            bind.chats.text = "You have not chats."
        }


        val scholarSuggestion = ArrayList<Scholar>()
//        for (scholar in scholarsList){
//            if (scholar.origin == realScholar!!.origin){
//                scholarSuggestion.add(scholar)
//            }
//        }

        suggestionAdapter.listDiffer.submitList(scholarSuggestion)




        return bind.root
    }

    private fun getScholar(pfNumber: String?){
        for (i in scholarsList.indices){
            if (pfNumber.contentEquals(scholarsList[i].pfNumber)){
                realScholar = scholarsList[i]
            }
            break
        }
    }


    private fun createBottomSheetDialog(inflater: LayoutInflater, container: ViewGroup?) {
        val view = inflater.inflate(R.layout.bottom_sheet, null, false)



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

    private fun setUpRecyclerView() {
//        bind.topRecView.apply {
//            adapter = suggestionAdapter
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        }

        bind.chatRecView.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        }
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.fab){
            bottomSheet.show()
        }
    }

    override fun onStop() {
        super.onStop()
        bottomSheet.dismiss()
    }

}