package com.example.githubsearch.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private var userFavourite: UserFavourite? = null
    private var isFavourite: Boolean = false

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_USER = "extra_user"

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


        val username = intent.getStringExtra(EXTRA_USERNAME);
        userFavourite = intent.getParcelableExtra(EXTRA_USER)

        detailViewModel = obtainViewModel(this@DetailActivity)

        if (username != null) {
            detailViewModel.setDetailUser(username)
        }

        userFavourite?.let { detailViewModel.setDataUserNow(it) }

        detailViewModel.checkIsFavourite(username).observe(this, { listUser ->
            detailViewModel.setIsFav(listUser.isNotEmpty())
            isFavourite = listUser.isNotEmpty()
        })

        detailViewModel.isFav.observe(this, {
            binding.btnFavourite.setImageResource(if (it) R.drawable.ic_star else R.drawable.ic_star_basic)
            isFavourite = it
        })

        detailViewModel.detailUser.observe(this, { user ->
            setRenderChange(user)
        })

        detailViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        detailViewModel.isUserFavourite.observe(this, {
            userFavourite = it
        })

        detailViewModel.snackbarText.observe(this, {
            Toast.makeText(
                this,
                it,
                Toast.LENGTH_SHORT
            ).show()
        })

        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = username

        binding.btnFavourite.setOnClickListener(this)
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

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnFavourite) {
            if (isFavourite) {
                userFavourite?.let { detailViewModel.delete(it) }
                showMessage("Berhasil menghapus dari favourite")
                detailViewModel.setIsFav(false)
            } else {
                userFavourite?.let { detailViewModel.insert(it) }
                showMessage("Berhasil menambah ke favourite")
                detailViewModel.setIsFav(true)
            }
            binding.btnFavourite.setImageResource(if (isFavourite) R.drawable.ic_star else R.drawable.ic_star_basic)
        }
    }

}