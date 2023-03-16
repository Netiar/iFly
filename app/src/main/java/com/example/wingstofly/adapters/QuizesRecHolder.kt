package com.example.wingstofly.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.databinding.QuestionsRecViewBinding
import com.example.wingstofly.models.ResultX

class QuizesRecHolder: RecyclerView.Adapter<QuizesRecHolder.MyHolder>() {
    inner class MyHolder(val bind: QuestionsRecViewBinding): RecyclerView.ViewHolder(bind.root){

        fun  setData(resultX: ResultX){

            bind.homeTeam.text = "${listDiffer.currentList.indexOf(resultX) + 1}. ${resultX.question}"
            bind.radioAns1.text = resultX.incorrect_answers[0]
            bind.radioAns2.text = resultX.incorrect_answers[1]
            bind.radioAns3.text = resultX.incorrect_answers[2]
            bind.radioAns4.text = resultX.correct_answer

            bind.readMore.setOnClickListener{
                bind.radioAns4.isChecked = true
            }
        }
    }
    private val diffUtil = object : DiffUtil.ItemCallback<ResultX>(){
        override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem.question == newItem.question
        }

        override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem == newItem
        }

    }

    val listDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  MyHolder(
        QuestionsRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listDiffer.currentList[position])
    }

    override fun getItemCount() = listDiffer.currentList.size
}