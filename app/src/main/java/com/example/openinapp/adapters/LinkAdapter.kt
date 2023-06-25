package com.example.openinapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openinapp.databinding.LinkItemBinding
import com.example.openinapp.models.RecentLink
import com.example.openinapp.models.TopLink
import kotlin.math.min

class LinkAdapter(
    private val topLinks: List<TopLink>,
    private val recentLinks: List<RecentLink>,
    private val from: String
) : RecyclerView.Adapter<LinkAdapter.LinkViewHolder>() {

    class LinkViewHolder(private val binding: LinkItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTop(link: TopLink) {
            Glide.with(binding.root.context).load(link.original_image).into(binding.image)

            binding.apply {
                title.text = link.title
                time.text = link.times_ago
                clicks.text = link.total_clicks.toString()
                smartLink.text = link.smart_link
                copySmartLink.setOnClickListener {}
            }
        }

        fun bindRecent(link: RecentLink) {
            Glide.with(binding.root.context).load(link.original_image).into(binding.image)

            binding.apply {
                title.text = link.title
                time.text = link.times_ago
                clicks.text = link.total_clicks.toString()
                smartLink.text = link.smart_link
                copySmartLink.setOnClickListener {}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val binding = LinkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LinkViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (from == "top")
            min(4, topLinks.size)
        else
            min(4, recentLinks.size)
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {

        if (from == "top") {
            holder.bindTop(topLinks[position])
        } else {
            holder.bindRecent(recentLinks[position])
        }
    }
}