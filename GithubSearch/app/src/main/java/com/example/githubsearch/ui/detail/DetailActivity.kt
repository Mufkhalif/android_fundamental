package com.example.githubsearch.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubsearch.R
import com.example.githubsearch.api.ResponseDetail
import com.example.githubsearch.adapter.SectionsPagerAdapter
import com.example.githubsearch.database.UserFavourite
import com.example.githubsearch.databinding.ActivityDetailBinding
import com.example.githubsearch.helper.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private var userFavourite: UserFavourite? = null
    private var favIcon: MenuItem? = null
    private var isFavourite: Boolean = false

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_USER = "extra_user"
        const val TAG = "DetailActivity"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionsPagerAdapter(this)

        with(binding) {
            viewPager.adapter = sectionPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val username = intent.getStringExtra(EXTRA_USERNAME);
        userFavourite = intent.getParcelableExtra(EXTRA_USER)
        detailViewModel = obtainViewModel(this@DetailActivity)

        if (username != null) {
            detailViewModel.setDetailUser(username)
        }

        detailViewModel.checkIsFavourite(username).observe(this, { listUser ->
            isFavourite = listUser.isNotEmpty()
            favIcon?.setIcon(if (isFavourite) R.drawable.ic_favourite_red else R.drawable.ic_favorite)
        })

        detailViewModel.detailUser.observe(this, { user ->
            setRenderChange(user)
        })

        detailViewModel.isLoading.observe(this, {
            showLoading(it)
        })

    }


    private fun setRenderChange(user: ResponseDetail) {
        with(binding) {
            tvName.text = user.name
            tvCompany.text = user.company
            tvLocation.text = user.location
            tvRepository.text = user.publicRepos.toString()
            tvFollower.text = user.followers.toString()
            tvFollowing.text = user.following.toString()
            tvUsername.text = user.login

            Glide.with(this@DetailActivity)
                .load(user.avatarUrl)
                .into(ivAvatar)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstace(activity.application)
        return ViewModelProvider(activity, factory).get(DetailViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        favIcon = menu.findItem(R.id.favourite)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.favourite -> {
                if (isFavourite) {
                    userFavourite?.let { detailViewModel.delete(it) }
                } else {
                    userFavourite?.let { detailViewModel.insert(it) }
                }

                isFavourite = !isFavourite
                favIcon?.setIcon(if (isFavourite) R.drawable.ic_favourite_red else R.drawable.ic_favorite)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}