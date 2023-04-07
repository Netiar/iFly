package com.example.wingstofly.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.wingstofly.R
import com.example.wingstofly.SingleActivity
import com.example.wingstofly.databinding.*
import com.example.wingstofly.models.Scholar
import com.skydoves.transformationlayout.TransformationCompat

class ScholarsRecAdapter(var context: Context): RecyclerView.Adapter<ScholarsRecAdapter.MyHolder>() {
    inner class MyHolder(private val bind: ViewBinding, val parent: ViewGroup): RecyclerView.ViewHolder(bind.root) {
        val v = ScholarBinding.inflate(LayoutInflater.from(context))
        val v1 = ScholarSuggestionBinding.inflate(LayoutInflater.from(context))

        fun setData(scholar:Scholar){
          if (v.root.id == bind.root.id){
              if(scholar.status == "Student"){
                  (bind as ScholarBinding).scholarStatus.text = scholar.secondarySchool
              }else{
                  if (scholar.workPlaces.size == 0){
                      (bind as ScholarBinding).scholarStatus.text = "${scholar.status}, at Not Mentioned"
                  }else{
                      (bind as ScholarBinding).scholarStatus.text = "${scholar.workPlaces[0].title} at ${scholar.workPlaces[0].department} "
                  }
              }
              bind.scholarName.text = scholar.name
              bind.status.text = scholar.status

              bind.scholarStatus.startAnimation(AnimationUtils.loadAnimation(
                  context, R.anim.zoom_in
              ))
              bind.scholarName.startAnimation(AnimationUtils.loadAnimation(
                  context, R.anim.zoom_in
              ))
              bind.status.startAnimation(AnimationUtils.loadAnimation(
                  context, R.anim.zoom_in
              ))
              bind.image.startAnimation(AnimationUtils.loadAnimation(
                  context, R.anim.zoom_in
              ))
              bind.root.setOnClickListener{
                  val testNumber = scholar.pfNumber!!.subSequence(0,2)
                  val intent = Intent(context, SingleActivity::class.java)

                  if ( testNumber.contentEquals("pf", ignoreCase = true)){
                      Toast.makeText(context, scholar.pfNumber!!.subSequence(0,2), Toast.LENGTH_SHORT ).show()
                      intent.putExtra("layout", 2)
                      intent.putExtra("scholar", scholar)
                      TransformationCompat.startActivity(bind.transformationLayout, intent)
                  }else{
                      Toast.makeText(context, scholar.pfNumber!!.subSequence(0,2), Toast.LENGTH_SHORT ).show()
                      intent.putExtra("layout", 2)
                      intent.putExtra("scholar", scholar)
                      TransformationCompat.startActivity(bind.transformationLayout, intent)
                  }
              }
          }
          if((v1.root.id == bind.root.id)){
              val newName = scholar.name!!.trim().substring(scholar.name!!.indexOf(" ") )
              (bind as ScholarSuggestionBinding).scholarName.text = "${scholar.name!!.substring(0, 1)}.$newName"
              bind.image.startAnimation(AnimationUtils.loadAnimation(
                  context, R.anim.zoom_in
              ))
              bind.scholarName.startAnimation(AnimationUtils.loadAnimation(
                  context, R.anim.zoom_in
              ))
              if (parent.id == FragmentChatBinding.inflate(LayoutInflater.from(context), parent, false).topView.id){
                  bind.root.setOnClickListener{
                      val intent = Intent(context, SingleActivity::class.java)
                      intent.putExtra("layout", 3)
                      intent.putExtra("scholar", scholar)
                      TransformationCompat.startActivity(bind.layout, intent)
                  }

              }
              if (parent.id == FragmentScholarBinding.inflate(LayoutInflater.from(context), parent, false).topRecView.id){
                  bind.root.setOnClickListener{
                      val intent = Intent(context, SingleActivity::class.java)
                      intent.putExtra("layout", 2)
                      intent.putExtra("scholar", scholar)
                      TransformationCompat.startActivity(bind.layout, intent)
                  }
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
        val view: ViewBinding = if (parent.id == FragmentScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false).root.id){
            ScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else if(parent.id == FragmentScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false).topRecView.id){
            ScholarSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else if(parent.id == FragmentChatBinding.inflate(LayoutInflater.from(parent.context), parent, false).topView.id){
            ScholarSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else{
            ScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
        return MyHolder(view, parent)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val scholar = listDiffer.currentList[position]
        holder.setData(scholar)
    }

    override fun getItemCount() = listDiffer.currentList.size
}