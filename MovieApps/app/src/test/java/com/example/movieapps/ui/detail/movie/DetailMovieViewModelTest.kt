package com.example.movieapps.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieapps.api.model.Movie
import com.example.movieapps.data.remote.MovieRepositorySecond
import com.example.movieapps.utils.DataDummy
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private val id = "550988"
    private lateinit var viewModel: DetailMovieViewModel

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepositorySecond

    @Mock
    private lateinit var observer: Observer<Movie>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = DataDummy.generateDummyMovies()[0]
        val movie = MutableLiveData<Movie>()
        movie.value = dummyMovie

        `when`(movieRepository.getDetailMovie(id)).thenReturn(movie)
        val movieEntities = viewModel.getDetailMovie(id).value

        verify(movieRepository).getDetailMovie(id)
        assertNotNull(movieEntities)
        assertEquals(dummyMovie.title, movieEntities?.title)
        assertEquals(dummyMovie.overview, movieEntities?.overview)
        assertEquals(dummyMovie.release_date, movieEntities?.release_date)
        assertEquals(dummyMovie.vote_average, movieEntities?.vote_average)
        assertEquals(dummyMovie.vote_count, movieEntities?.vote_count)
        assertEquals(dummyMovie.poster_path, movieEntities?.poster_path)

        viewModel.getDetailMovie(id).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}