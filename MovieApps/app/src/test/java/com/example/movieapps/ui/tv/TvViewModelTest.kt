package com.example.movieapps.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieapps.api.model.Status
import com.example.movieapps.api.response.ResponseTv
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
class TvViewModelTest {
    private lateinit var viewModel: TvViewModel
    private var dummyTvs = DataDummy.generateTvs()

    private val dummyResponseTv = ResponseTv(
        10,
        dummyTvs,
        20,
        Status(true)
    )

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepositorySecond

    @Mock
    private lateinit var observer: Observer<ResponseTv>

    @Before
    fun setUp() {
        viewModel = TvViewModel(movieRepository)
    }

    @Test
    fun getTvs() {
        val tvResult = MutableLiveData<ResponseTv>()
        tvResult.value = dummyResponseTv

        `when`(movieRepository.getTvs()).thenReturn(tvResult)
        val tvEntities = viewModel.getTv().value
        verify(movieRepository).getTvs()
        assertNotNull(tvEntities)
        assertEquals(dummyResponseTv.page, tvEntities?.page)
        assertEquals(dummyResponseTv.results.size, tvEntities?.results?.size)
        assertEquals(dummyResponseTv.total_results, tvEntities?.total_results)
        assertEquals(dummyResponseTv.success.status, tvEntities?.success?.status)

        viewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyResponseTv)
    }

}