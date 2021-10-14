package com.example.movieapps.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.movieapps.data.local.entity.Status
import com.example.movieapps.api.response.ResponseTv
import com.example.movieapps.data.MovieRepositorySecond
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.local.entity.Tv
import com.example.movieapps.utils.DataDummy
import com.example.movieapps.vo.Resource
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
class TvViewModelTest {
    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepositorySecond

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<Tv>>>

    @Mock
    private lateinit var pagedList: PagedList<Tv>

    @Before
    fun setUp() {
        viewModel = TvViewModel(movieRepository)
    }

    @Test
    fun getTvs() {
        val dummyTvs = Resource.success(pagedList)
        `when`(dummyTvs.data?.size).thenReturn(5)
        val tvs = MutableLiveData<Resource<PagedList<Tv>>>()
        tvs.value = dummyTvs

        `when`(movieRepository.getTvs()).thenReturn(tvs)
        val tvEntities = viewModel.getTv().value?.data
        verify(movieRepository).getTvs()
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        viewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyTvs)
    }

}