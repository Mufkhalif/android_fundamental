package com.example.movieapps.ui.bookmark

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapps.R
import com.example.movieapps.databinding.FragmentBookmarkBinding
import com.example.movieapps.ui.movie.MovieAdapter
import com.example.movieapps.ui.tv.TvAdapter
import com.example.movieapps.viewmodel.ViewModelFactory

class BookmarkFragment : Fragment() {
    private var _fragmentMovieBinding: FragmentBookmarkBinding? = null
    private val binding get() = _fragmentMovieBinding

    private lateinit var viewModel: BookmarkViewModel
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var tvAdapter: TvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _fragmentMovieBinding = FragmentBookmarkBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            movieAdapter = MovieAdapter()
            tvAdapter = TvAdapter()
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]
            showBookmarkMovies()
        }
    }

    private fun showBookmarkMovies() {
        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.getMoviesBookmarked().observe(viewLifecycleOwner, { movies ->
            binding?.progressBar?.visibility = View.GONE
            if (movies != null) {
                binding?.progressBar?.visibility = View.GONE
                movieAdapter.submitList(movies)
            }
        })

        binding?.rvBookmark?.layoutManager = LinearLayoutManager(context)
        binding?.rvBookmark?.setHasFixedSize(true)
        binding?.rvBookmark?.adapter = movieAdapter
    }

    private fun showBookmarkTv() {
        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.getTvsBookmarked().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                binding?.progressBar?.visibility = View.GONE
                tvAdapter.submitList(movies)
            }
        })

        binding?.rvBookmark?.layoutManager = LinearLayoutManager(context)
        binding?.rvBookmark?.setHasFixedSize(true)
        binding?.rvBookmark?.adapter = tvAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_movie -> showBookmarkMovies()
            R.id.action_tv -> showBookmarkTv()
        }
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieBinding = null
    }

}