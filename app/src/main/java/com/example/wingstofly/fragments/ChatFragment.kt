package com.example.wingstofly.fragments

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.adapters.BottomSheetRecAdapter
import com.example.wingstofly.adapters.ChatRecHolder
import com.example.wingstofly.adapters.ScholarsRecAdapter
import com.example.wingstofly.databinding.BottomSheetBinding
import com.example.wingstofly.databinding.FragmentChatBinding
import com.example.wingstofly.databinding.ScholarBinding
import com.example.wingstofly.models.Scholar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_single_job.view.*
import kotlin.apply


class ChatFragment : Fragment(), View.OnClickListener {
    private lateinit var bind:FragmentChatBinding
    private lateinit var recBind: ScholarBinding
    private lateinit var suggestionAdapter: ScholarsRecAdapter
    private lateinit var chatAdapter: ChatRecHolder
    private lateinit var navController: NavController
    private lateinit var bottomSheet: BottomSheetDialog
    private lateinit var scholarsList: List<Scholar>
    private lateinit var scholarsAdapter: BottomSheetRecAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bind = FragmentChatBinding.inflate(inflater)
        recBind = ScholarBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)
        scholarsList = (activity as MainActivity).scholars
        chatAdapter = ChatRecHolder()
        scholarsAdapter = BottomSheetRecAdapter(requireContext())


        bottomSheet = BottomSheetDialog(requireContext())
        createBottomSheetDialog(inflater, container)
//        bottomSheet.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        suggestionAdapter = ScholarsRecAdapter(requireContext())

        bind.fab.setOnClickListener(this::onClick)

        val realScholar = Scholar("Charles Muvaka", "Student")
        realScholar.apply {
            origin = "Kericho Branch"
            primarySchool = "Umoja primary school"
            secondarySchool = "St Josephs Seminary Mwingi"
            varsity = "The Technical University of Kenya"
        }
        val scholarSuggestion = ArrayList<Scholar>()
        for (scholar in scholarsList){
            if (scholar.origin == realScholar.origin){
                scholarSuggestion.add(scholar)
            }
        }

        suggestionAdapter.listDiffer.submitList(scholarSuggestion)

        setUpRecyclerView()


        return bind.root
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
        bind.topRecView.apply {
            adapter = suggestionAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

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


}