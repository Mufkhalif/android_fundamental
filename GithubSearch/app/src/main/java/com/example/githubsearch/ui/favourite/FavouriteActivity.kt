package com.example.githubsearch.ui.favourite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearch.R
import com.example.githubsearch.adapter.FavouriteAdapter
import com.example.githubsearch.databinding.ActivityFavouriteBinding
import com.example.githubsearch.helper.ViewModelFactory
import com.example.githubsearch.ui.detail.DetailViewModel

class FavouriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var favouriteViewModel: FavouriteViewModel
    private lateinit var adapater: FavouriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favouriteViewModel = obtainViewModel(this@FavouriteActivity)
        favouriteViewModel.getAllFavourite().observe(this, { listUser ->
            if (listUser != null) {
                adapater.setListFav(listUser)
            }
        })

        adapater = FavouriteAdapter()

        binding.rvFavourite.layoutManager = LinearLayoutManager(this)
        binding.rvFavourite.setHasFixedSize(true)
        binding.rvFavourite.adapter = adapater

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun obtainViewModel(activity: AppCompatActivity): FavouriteViewModel {
        val factory = ViewModelFactory.getInstace(activity.application)
        return ViewModelProvider(activity, factory).get(FavouriteViewModel::class.java)
    }
}