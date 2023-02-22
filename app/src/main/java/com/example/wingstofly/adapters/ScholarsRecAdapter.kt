package com.example.wingstofly.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.wingstofly.SingleActivity
import com.example.wingstofly.databinding.*
import com.example.wingstofly.models.Scholar
import kotlinx.android.synthetic.main.fragment_chat.view.top_rec_view
import kotlinx.android.synthetic.main.fragment_scholar.view.*

class ScholarsRecAdapter(var context: Context): RecyclerView.Adapter<ScholarsRecAdapter.MyHolder>() {
    inner class MyHolder(private val bind: ViewBinding): RecyclerView.ViewHolder(bind.root) {
        val v = ScholarBinding.inflate(LayoutInflater.from(context))
        val v1 = ScholarSuggestionBinding.inflate(LayoutInflater.from(context))

        fun setData(scholar:Scholar){
          if (v.root.id == bind.root.id){
              (bind as ScholarBinding).scholarStatus.text = scholar.secondarySchool
              bind.scholarName.text = scholar.name
              bind.status.text = scholar.status

              bind.root.setOnClickListener{
                  val bundle = Bundle().apply {
                      putSerializable("scholar", scholar)
                      putInt("layoutNumber", 2)
                  }
                  val testNumber = scholar.pfNumber!!.subSequence(0,2)

                  if ( testNumber.contentEquals("pf", ignoreCase = true)){
                      Toast.makeText(context, scholar.pfNumber!!.subSequence(0,2), Toast.LENGTH_SHORT ).show()
                      val intent = Intent(context, SingleActivity::class.java)
                      intent.putExtra("layout", 2)
                      intent.putExtra("scholar", scholar)
                      context.startActivity(intent)
//                      it.findFragment<ScholarFragment>().findNavController().navigate(R.id.action_scholarFragment2_to_singleActivity, bundle)
                  }else{
                      Toast.makeText(context, scholar.pfNumber!!.subSequence(0,2), Toast.LENGTH_SHORT ).show()
                      val intent = Intent(context, SingleActivity::class.java)
                      intent.putExtra("layout", 2)
                      intent.putExtra("scholar", scholar)
                      context.startActivity(intent)
//                      it.findFragment<ScholarFragment>().findNavController().navigate(R.id.action_scholarFragment_to_singleActivity2, bundle)
                  }
              }
          }
          if((v1.root.id == bind.root.id)){
              val newName = scholar.name!!.trim().substring(scholar.name!!.indexOf(" ") )
              (bind as ScholarSuggestionBinding).scholarName.text = "${scholar.name!!.substring(0, 1)}.$newName"
              bind.root.setOnClickListener{
                  val bundle = Bundle().apply {
                      putSerializable("scholar", scholar)
                  }
//                  it.findFragment<ScholarFragment>().findNavController().navigate(R.id.action_scholarFragment2_to_singleActivity, bundle)
              }
          }

        }
    }

    private val diffUtil = object: DiffUtil.ItemCallback<Scholar>(){
        override fun areItemsTheSame(oldItem: Scholar, newItem: Scholar): Boolean {
            return oldItem.pfNumber == newItem.pfNumber
        }

        override fun areContentsTheSame(oldItem: Scholar, newItem: Scholar): Boolean {
            return oldItem.equals(newItem)
        }

    }

    val listDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view: ViewBinding? = null
        if (parent.id == FragmentScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false).root.myRecView.id){
            view = ScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else if(parent.id == FragmentScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false).root.top_rec_view.id){
            view = ScholarSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else if(parent.id == FragmentChatBinding.inflate(LayoutInflater.from(parent.context), parent, false).root.top_rec_view.id){
            view = ScholarSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else{
            view = ScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val scholar = listDiffer.currentList[position]
        holder.setData(scholar)
    }

    override fun getItemCount() = listDiffer.currentList.size
}