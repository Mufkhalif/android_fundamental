package com.example.movieapps.ui.detail.tv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movieapps.R
import com.example.movieapps.api.Constants
import com.example.movieapps.data.local.entity.Tv
import com.example.movieapps.databinding.ActivityDetailTvBinding
import com.example.movieapps.viewmodel.ViewModelFactory
import com.example.movieapps.vo.Status

class DetailTvActivity : AppCompatActivity() {
    private lateinit var detailTvActivity: ActivityDetailTvBinding
    private lateinit var viewModel: DetailTvViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailTvActivity = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(detailTvActivity.root)

        viewModel = obtainViewModel(this@DetailTvActivity)
        val extras = intent.extras

        if (extras != null) {
            val tvId = extras.getString(EXTRA_TV)
            if (tvId != null) {

                viewModel.setSelectedTv(tvId)
                viewModel.tv.observe(this, { tv ->
                    if (tv != null) {
                        when (tv.status) {
                            Status.LOADING -> detailTvActivity.progressBar.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> {
                                detailTvActivity.progressBar.visibility = View.GONE
                                tv.data?.let { setRenderContent(it) }
                            }
                            Status.ERROR -> {
                                detailTvActivity.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    applicationContext,
                                    getString(R.string.network_error),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    }
                })
            }
        }

        supportActionBar?.title = getString(R.string.detail_tv)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailTvViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DetailTvViewModel::class.java)
    }

    private fun setRenderContent(tv: Tv) {
        with(detailTvActivity) {
            textTitle.text = tv.name
            textDate.text = tv.first_air_date
            textRating.text = tv.popularity.toString()
            textOverview.text = tv.overview
            textVote.text = tv.vote_count.toString()
        }

        Glide.with(this)
            .load("${Constants.baseImageUrl}${tv.poster_path}")
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(detailTvActivity.cvPoster)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        viewModel.tv.observe(this, { tv ->
            if (tv != null) {
                when (tv.status) {
                    Status.LOADING -> detailTvActivity.progressBar.visibility =
                        View.VISIBLE
                    Status.SUCCESS -> {
                        detailTvActivity.progressBar.visibility = View.GONE
                        tv.data?.let { setBookmarked(it.bookmarked) }
                    }
                    Status.ERROR -> {
                        detailTvActivity.progressBar.visibility = View.GONE
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.network_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            viewModel.setBookmark()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarked(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white)
        }
    }

    companion object {
        const val EXTRA_TV = "extra_tv"
    }
}