package com.example.palindromechecker.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.palindromechecker.data.response.DataItem
import com.example.palindromechecker.databinding.ItemListuserBinding

class UserPagingAdapter:PagingDataAdapter<DataItem,UserPagingAdapter.MyHolder>(DIFF_CALLBACK){

    private lateinit var onItemClickAdapter : OnItemClickAdapter
    fun setItemClickAdapter(onItemClickAdapter : OnItemClickAdapter){
        this.onItemClickAdapter = onItemClickAdapter
    }

    class MyHolder(private val binding:ItemListuserBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: DataItem){
            Glide.with(binding.root.context)
                .load(dataItem.avatar)
                .centerCrop()
                .into(binding.ivListuser)
            binding.tvListname.text = "${dataItem.firstName} ${dataItem.lastName}"
            binding.tvListemail.text = dataItem.email
        }

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClickAdapter.onItemClick(data!!)
        }
        holder.bind(data!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemListuserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyHolder(binding)
    }


    interface OnItemClickAdapter{
        fun onItemClick(datta: DataItem)
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}