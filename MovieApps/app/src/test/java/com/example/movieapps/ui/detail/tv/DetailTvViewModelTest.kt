package com.example.movieapps.ui.detail.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieapps.api.model.Tv
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
class DetailTvViewModelTest {
    private val id = "550988"
    private lateinit var viewModel: DetailTvViewModel

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepositorySecond

    @Mock
    private lateinit var observer: Observer<Tv>

    @Before
    fun setUp() {
        viewModel = DetailTvViewModel(movieRepository)
    }

    @Test
    fun getTvDetail() {
        val dummyTv = DataDummy.generateTvs()[0]
        val tv = MutableLiveData<Tv>()
        tv.value = dummyTv

        `when`(movieRepository.getDetailTv(id)).thenReturn(tv)
        val tvEntities = viewModel.getTvDetail(id).value
        verify(movieRepository).getDetailTv(id)
        assertNotNull(tvEntities)

        assertEquals(dummyTv.name, tvEntities?.name)
        assertEquals(dummyTv.first_air_date, tvEntities?.first_air_date)
        assertEquals(dummyTv.overview, tvEntities?.overview)
        assertEquals(dummyTv.poster_path, tvEntities?.poster_path)
        assertEquals(dummyTv.vote_average, tvEntities?.vote_average)
        assertEquals(dummyTv.popularity, tvEntities?.popularity)

        viewModel.getTvDetail(id).observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}