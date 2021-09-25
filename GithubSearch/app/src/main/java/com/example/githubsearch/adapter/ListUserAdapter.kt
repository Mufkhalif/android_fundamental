package com.example.githubsearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubsearch.api.User
import com.example.githubsearch.databinding.ItemSearchBinding

class ListUserAdapter(private val listSearch: List<User>) :
    RecyclerView.Adapter<ListUserAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listSearch[position]

        with(holder) {
            binding.iName.text = user.login

            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .into(binding.iAvatar)

            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(user)
            }

        }

    }

    override fun getItemCount() = listSearch.size

    inner class ViewHolder(val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root)

}