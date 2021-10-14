package com.example.movieapps.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.MovieRepositorySecond
import com.example.movieapps.utils.DataDummy
import com.example.movieapps.vo.Resource
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id.toString()

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepositorySecond

    @Mock
    private lateinit var observer: Observer<Resource<Movie>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val detailMovie = Resource.success(dummyMovie)
        val movie = MutableLiveData<Resource<Movie>>()
        movie.value = detailMovie

        `when`(movieRepository.getDetailMovie(movieId)).thenReturn(movie)

        viewModel.movie.observeForever(observer)
        verify(observer).onChanged(detailMovie)
    }
}