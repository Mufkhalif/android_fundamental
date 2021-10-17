package com.example.movieapps.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.movieapps.data.MovieRepositorySecond
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.local.entity.Tv
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when`

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {
    private lateinit var viewModel: BookmarkViewModel

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepositorySecond

    @Mock
    private lateinit var observerMovie: Observer<PagedList<Movie>>

    @Mock
    private lateinit var observerTv: Observer<PagedList<Tv>>

    @Mock
    private lateinit var pagedListMovie: PagedList<Movie>

    @Mock
    private lateinit var pagedListTv: PagedList<Tv>

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(movieRepository)
    }

    @Test
    fun getMoviesBookmarked() {
        val dummyMovies = pagedListMovie
        `when`(dummyMovies.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<Movie>>()
        movies.value = dummyMovies

        `when`(movieRepository.getMoviesBookmarked()).thenReturn(movies)
        val moviesEntities = viewModel.getMoviesBookmarked().value
        verify(movieRepository).getMoviesBookmarked()
        assertNotNull(moviesEntities)
        assertEquals(5, moviesEntities?.size)

        viewModel.getMoviesBookmarked().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovies)
    }

    @Test
    fun getTvsBookmarked() {
        val dummyTvs = pagedListTv
        `when`(dummyTvs.size).thenReturn(5)
        val tvs = MutableLiveData<PagedList<Tv>>()
        tvs.value = dummyTvs

        `when`(movieRepository.getTvsBookmarked()).thenReturn(tvs)
        val tvEntities = viewModel.getTvsBookmarked().value
        verify(movieRepository).getTvsBookmarked()
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        viewModel.getTvsBookmarked().observeForever(observerTv)
        verify(observerTv).onChanged(dummyTvs)
    }
}