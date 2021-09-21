package com.example.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.example.githubsearch.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

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

        val username = intent.getStringExtra(EXTRA_USERNAME);
        if (username != null) {
            detailViewModel.setDetailUser(username)
        }

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
            Glide.with(this@DetailActivity)
                .load(user.avatarUrl)
                .into(ivAvatar)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}