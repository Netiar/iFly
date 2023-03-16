package com.example.wingstofly.fragments

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingstofly.SingleActivity
import com.example.wingstofly.adapters.QuizesRecHolder
import com.example.wingstofly.databinding.FragmentQuizesBinding
import com.example.wingstofly.models.TriviaCategory
import com.example.wingstofly.utils.Constants
import com.example.wingstofly.utils.Resource

class QuestionsFragment: Fragment(), View.OnClickListener {
    private lateinit var bind: FragmentQuizesBinding
    private lateinit var pref: SharedPreferences
    private var category:TriviaCategory? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getSerializable("category") as TriviaCategory
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentQuizesBinding.inflate(layoutInflater)

        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val pfNumber = pref.getString(Constants.PF_NUMBER, null)
        val scholars = (activity as SingleActivity).scholars

        for (i in 0 until scholars.size){
            if(scholars[i].pfNumber == pfNumber){
                bind.scholarName.text = scholars[i].name
            }
        }
        val viewModel = (activity as SingleActivity).questViewModel
        val quizesRecHolder = QuizesRecHolder()

        bind.scholarStatus.text = "${category!!.name} Questions"


        viewModel.questions.observe(viewLifecycleOwner, Observer { questions ->
            when(questions){
                is Resource.Success -> {
                    questions.newsData?.let {
                        quizesRecHolder.listDiffer.submitList(it.results)
                        bind.progress.isVisible = false
                        bind.recView.apply {
                            adapter = quizesRecHolder
                            layoutManager = LinearLayoutManager(requireContext())
                            setHasFixedSize(true)
                        }

                    }
                }
                is Resource.Failure -> {
                    questions.questMessage?.let{
                        bind.progress.isVisible = true
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()

                    }
                }
                is Resource.Loading -> {
                    bind.progress.isVisible = true
                    Toast.makeText(context, "questions loading", Toast.LENGTH_LONG).show()

                }
            }

        })

        bind.back.setOnClickListener(this::onClick)


        return bind.root
    }

    companion object{
        fun newInstance(cat: TriviaCategory) =
            QuestionsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("category", cat)
                }
            }
    }

    override fun onClick(p0: View?) {
        if(p0 == bind.back){
            requireActivity().finish()
        }
    }
}