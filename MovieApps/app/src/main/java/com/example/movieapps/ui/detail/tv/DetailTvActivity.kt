package com.example.movieapps.ui.detail.tv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movieapps.R
import com.example.movieapps.data.TvEntity
import com.example.movieapps.databinding.ActivityDetailTvBinding

class DetailTvActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV = "extra_tv"
    }

    private lateinit var detailTvActivity: ActivityDetailTvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailTvActivity = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(detailTvActivity.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailTvViewModel::class.java]

        val extras = intent.extras

        if (extras != null) {
            val tvId = extras.getString(EXTRA_TV)
            if (tvId != null) {
                viewModel.setCurrentId(tvId)
                val movie = viewModel.getTvDetail()
                setRenderContent(movie)
            }
        }

        supportActionBar?.title = "Detail Tv"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setRenderContent(tv: TvEntity) {
        with(detailTvActivity) {
            textTitle.text = tv.name
            textDate.text = tv.firstAirDate
            textRating.text = tv.popularity.toString()
            textOverview.text = tv.overview
            textVote.text = tv.voteCount.toString()
        }

        Glide.with(this)
            .load(tv.posterPath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(detailTvActivity.cvPoster)
    }
}