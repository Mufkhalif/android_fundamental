package com.example.githubsearch.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubsearch.database.UserFavourite
import com.example.githubsearch.databinding.ItemSearchBinding
import com.example.githubsearch.helper.FavouriteDiffCallback
import com.example.githubsearch.ui.detail.DetailActivity

class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {
    private val listUserFavourite = ArrayList<UserFavourite>()

    fun setListFav(listUserFavourite: List<UserFavourite>) {
        val diffCallback = FavouriteDiffCallback(this.listUserFavourite, listUserFavourite)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listUserFavourite.clear()
        this.listUserFavourite.addAll(listUserFavourite)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteAdapter.FavouriteViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteAdapter.FavouriteViewHolder, position: Int) {
        val user = listUserFavourite[position]

        with(holder) {
            binding.iName.text = user.login

            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .into(binding.iAvatar)

            itemView.setOnClickListener {
                val detailIntent = Intent(it.context, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.EXTRA_USERNAME, user.login)

                val userFavourite = UserFavourite(
                    id = user.id,
                    login = user.login,
                    avatarUrl = user.avatarUrl
                )
                detailIntent.putExtra(DetailActivity.EXTRA_USER, userFavourite)
                it.context.startActivity(detailIntent)
            }

        }

    }

    override fun getItemCount(): Int {
        return listUserFavourite.size
    }

    inner class FavouriteViewHolder(val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root)

}