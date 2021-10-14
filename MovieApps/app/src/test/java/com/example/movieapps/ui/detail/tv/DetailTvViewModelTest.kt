package com.example.movieapps.ui.detail.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieapps.data.local.entity.Tv
import com.example.movieapps.data.MovieRepositorySecond
import com.example.movieapps.utils.DataDummy
import com.example.movieapps.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvViewModelTest {
    private lateinit var viewModel: DetailTvViewModel
    private val dummyTv = DataDummy.generateTvs()[0]
    private val tvId = dummyTv.id.toString()

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepositorySecond

    @Mock
    private lateinit var observer: Observer<Resource<Tv>>

    @Before
    fun setUp() {
        viewModel = DetailTvViewModel(movieRepository)
        viewModel.setSelectedTv(tvId)
    }

    @Test
    fun getTvDetail() {
        val detailTv = Resource.success(dummyTv)
        val tv = MutableLiveData<Resource<Tv>>()
        tv.value = detailTv

        `when`(
            movieRepository.getDetailTv(tvId)
        ).thenReturn(tv)


        viewModel.tv.observeForever(observer)
        verify(observer).onChanged(detailTv)
    }
}