package com.example.movieapps.ui.detail.movie

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
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.databinding.ActivityDetailMovieBinding
import com.example.movieapps.viewmodel.ViewModelFactory
import com.example.movieapps.vo.Status

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var detailMovieBinding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailMovieBinding.root)

        viewModel = obtainViewModel(this@DetailMovieActivity)
        val extras = intent.extras

        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {

                viewModel.setSelectedMovie(movieId)
                viewModel.movie.observe(this, { movie ->
                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> detailMovieBinding.progressBar.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> {
                                detailMovieBinding.progressBar.visibility = View.GONE
                                movie.data?.let { setRenderContent(it) }
                            }
                            Status.ERROR -> {
                                detailMovieBinding.progressBar.visibility = View.GONE
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

        supportActionBar?.title = getString(R.string.detail_movie)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        viewModel.movie.observe(this, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> detailMovieBinding.progressBar.visibility =
                        View.VISIBLE
                    Status.SUCCESS -> {
                        detailMovieBinding.progressBar.visibility = View.GONE
                        movie.data?.let {
                            setBookmarked(it.bookmarked)
                        }
                    }
                    Status.ERROR -> {
                        detailMovieBinding.progressBar.visibility = View.GONE
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
        const val EXTRA_MOVIE = "extra_movie"
    }

}