package com.example.wingstofly.adapters

import android.content.Context
import android.content.Intent
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.SingleActivity
import com.example.wingstofly.databinding.ScholarBinding
import com.example.wingstofly.models.Scholar
import com.skydoves.transformationlayout.TransformationCompat

class BottomSheetRecAdapter(val cont: Context): RecyclerView.Adapter<BottomSheetRecAdapter.MyHolder>() {
    inner class MyHolder( val bind: ScholarBinding): RecyclerView.ViewHolder(bind.root){
        fun setData(scholar: Scholar){
            bind.scholarStatus.text = scholar.secondarySchool
            bind.scholarName.text = scholar.name
            bind.status.text = scholar.status

            bind.root.setOnClickListener{
                val intent = Intent(cont, SingleActivity::class.java)
                intent.putExtra("layout", 3)
                intent.putExtra("scholar", scholar)
                TransformationCompat.startActivity(bind.transformationLayout, intent)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        ScholarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listDiffer.currentList[position])
    }

    override fun getItemCount() = listDiffer.currentList.size
}