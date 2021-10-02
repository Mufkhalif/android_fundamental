package com.example.movieapps.ui.detail.movie

import com.example.movieapps.utils.DataDummy
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovies = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovies.id

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovies.id)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovies.title, movieEntity.title)
        assertEquals(dummyMovies.overview, movieEntity.overview)
        assertEquals(dummyMovies.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovies.voteAverage, movieEntity.voteAverage)
        assertEquals(dummyMovies.voteCount, movieEntity.voteCount)
        assertEquals(dummyMovies.voteCount, movieEntity.voteCount)
        assertEquals(dummyMovies.posterPath, movieEntity.posterPath)
    }
}