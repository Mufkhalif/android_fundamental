package com.example.githubsearch.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.githubsearch.database.UserFavourite

class FavouriteDiffCallback(
    private val mOldFavList: List<UserFavourite>,
    private val mNewFavList: List<UserFavourite>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavList[oldItemPosition].id == mNewFavList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val mOldFavList = mOldFavList[oldItemPosition]
        val mNewFavList = mNewFavList[newItemPosition]
        return mOldFavList.login == mNewFavList.login
    }
}