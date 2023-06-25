package com.example.openinapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openinapp.databinding.ItemBinding
import com.example.openinapp.models.Item

class ItemAdapter(private val list: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item) {
            binding.text1.text = item.subTitle
            binding.text2.text = item.title

            Glide.with(binding.root.context).load(item.icon).into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = list[position]
        item.let {
          holder.bind(it)
        }
    }
}