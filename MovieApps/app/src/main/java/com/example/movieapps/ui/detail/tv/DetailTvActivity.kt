package com.example.movieapps.ui.detail.tv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movieapps.R
import com.example.movieapps.api.Constants
import com.example.movieapps.api.model.Tv
import com.example.movieapps.databinding.ActivityDetailTvBinding
import com.example.movieapps.viewmodel.ViewModelFactory

class DetailTvActivity : AppCompatActivity() {

    private lateinit var detailTvActivity: ActivityDetailTvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailTvActivity = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(detailTvActivity.root)

        val viewModel = obtainViewModel(this@DetailTvActivity)
        val extras = intent.extras

        if (extras != null) {
            val tvId = extras.getString(EXTRA_TV)
            if (tvId != null) {
                detailTvActivity.progressBar.visibility = View.VISIBLE
                viewModel.getTvDetail(tvId).observe(this, { tv ->
                    if (tv != null) {
                        setRenderContent(tv)
                    }
                    detailTvActivity.progressBar.visibility = View.GONE
                })
            }
        }

        supportActionBar?.title = "Detail Tv"
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

    companion object {
        const val EXTRA_TV = "extra_tv"
    }
}