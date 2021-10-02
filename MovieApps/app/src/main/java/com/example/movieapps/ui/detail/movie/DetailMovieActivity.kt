package com.example.movieapps.ui.detail.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movieapps.R
import com.example.movieapps.data.MovieEntity
import com.example.movieapps.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var detailMovieBinding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailMovieBinding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieViewModel::class.java]

        val extras = intent.extras

        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                val movie = viewModel.getMovie()
                setRenderContent(movie)
            }
        }

        supportActionBar?.title = "Detail Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setRenderContent(movie: MovieEntity) {
        with(detailMovieBinding) {
            textTitle.text = movie.title
            textDate.text = movie.releaseDate
            textRating.text = movie.popularity.toString()
            textOverview.text = movie.overview
            textVote.text = movie.voteCount.toString()
        }

        Glide.with(this)
            .load(movie.posterPath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(detailMovieBinding.cvPoster)
    }
}