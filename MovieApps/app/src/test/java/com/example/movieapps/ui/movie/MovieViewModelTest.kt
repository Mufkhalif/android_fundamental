package com.example.movieapps.ui.movie

import com.example.movieapps.data.MovieEntity
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }

    @Test
    fun getEmptyMovies() {
        val movies = ArrayList<MovieEntity>()
        assertEquals(0, movies.size)
    }
}