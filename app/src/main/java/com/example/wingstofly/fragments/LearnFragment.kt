package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.MainActivity
import com.example.wingstofly.adapters.QuestRecAdapter
import com.example.wingstofly.adapters.UpskillRecHolder
import com.example.wingstofly.databinding.FragmentLearnBinding
import com.example.wingstofly.utils.Resource
import com.example.wingstofly.viewmodel.QuizViewModel

class LearnFragment : Fragment() {
    private lateinit var bind:FragmentLearnBinding
    private lateinit var viewModel: QuizViewModel
    private lateinit var topicsAdapter: QuestRecAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        bind = FragmentLearnBinding.inflate(layoutInflater)

        val schools = (activity as MainActivity).schools
        val schoolsRecHolder = UpskillRecHolder(requireContext())
        schoolsRecHolder.asyncList.submitList(schools)

        bind.recView2.apply {
            adapter = schoolsRecHolder
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        viewModel = (activity as MainActivity).questViewModel
        topicsAdapter = QuestRecAdapter(requireContext())
        setUpRecView()

        viewModel.topics.observe(viewLifecycleOwner, Observer { topicCategories ->
            when(topicCategories){
                is Resource.Success -> {
                    topicCategories.newsData?.let {
                        bind.progress.isVisible = false
                        topicsAdapter.listDiffer.submitList(it.trivia_categories)

                    }
                }

                is Resource.Failure ->{
                    topicCategories.questMessage?.let { message ->
                        bind.progress.isVisible = true
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                    bind.progress.isVisible = true
                    Toast.makeText(context, "messages loading", Toast.LENGTH_LONG).show()
                }
            }

        })


        return bind.root
    }

    private fun setUpRecView(){

        bind.recView1.apply {
            adapter = topicsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

}