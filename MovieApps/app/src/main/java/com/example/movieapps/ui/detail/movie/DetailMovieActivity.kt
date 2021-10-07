package com.example.movieapps.ui.detail.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movieapps.R
import com.example.movieapps.api.Constants
import com.example.movieapps.api.model.Movie
import com.example.movieapps.databinding.ActivityDetailMovieBinding
import com.example.movieapps.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var detailMovieBinding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailMovieBinding.root)

        val viewModel = obtainViewModel(this@DetailMovieActivity)
        val extras = intent.extras

        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                detailMovieBinding.progressBar.visibility = View.VISIBLE
                viewModel.getDetailMovie(movieId).observe(this, { movie ->
                    if (movie != null) {
                        setRenderContent(movie)
                    }
                    detailMovieBinding.progressBar.visibility = View.GONE
                })
            }
        }

        supportActionBar?.title = "Detail Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailMovieViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DetailMovieViewModel::class.java)
    }

    private fun setRenderContent(movie: Movie) {
        with(detailMovieBinding) {
            textTitle.text = movie.title
            textDate.text = movie.release_date
            textRating.text = movie.popularity.toString()
            textOverview.text = movie.overview
            textVote.text = movie.vote_count.toString()
        }

        Glide.with(this)
            .load("${Constants.baseImageUrl}${movie.poster_path}")
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(detailMovieBinding.cvPoster)
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

}