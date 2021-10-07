package com.example.movieapps.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieapps.api.response.ResponseMovie
import com.example.movieapps.data.remote.MovieRepositorySecond
import com.example.movieapps.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private var dummyMovies = DataDummy.generateDummyMovies()

    private val dummyResponseMovie = ResponseMovie(
        10,
        dummyMovies,
        20
    )

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepositorySecond

    @Mock
    private lateinit var observer: Observer<ResponseMovie>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val movieResult = MutableLiveData<ResponseMovie>()
        movieResult.value = dummyResponseMovie

        `when`(movieRepository.getMovies()).thenReturn(movieResult)
        val moviesEntities = viewModel.getMovies().value
        verify(movieRepository).getMovies()
        assertNotNull(moviesEntities)
        assertEquals(dummyResponseMovie.results.size, moviesEntities?.results?.size)
        assertEquals(dummyResponseMovie.page, moviesEntities?.page)
        assertEquals(dummyResponseMovie.total_pages, moviesEntities?.total_pages)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyResponseMovie)
    }

}