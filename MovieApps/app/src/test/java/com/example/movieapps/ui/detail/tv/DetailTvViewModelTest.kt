package com.example.movieapps.ui.detail.tv

import com.example.movieapps.utils.DataDummy
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailTvViewModelTest {
    private lateinit var viewModel: DetailTvViewModel
    private val dummyTv = DataDummy.generateTvs()[0]
    private val tvId = dummyTv.id

    @Before
    fun setUp() {
        viewModel = DetailTvViewModel()
        viewModel.setCurrentId(tvId)
    }

    @Test
    fun getTvDetail() {
        viewModel.setCurrentId(dummyTv.id)
        val tvEntity = viewModel.getTvDetail()
        assertNotNull(tvEntity)
        assertEquals(dummyTv.name, tvEntity.name)
        assertEquals(dummyTv.firstAirDate, tvEntity.firstAirDate)
        assertEquals(dummyTv.overview, tvEntity.overview)
        assertEquals(dummyTv.posterPath, tvEntity.posterPath)
        assertEquals(dummyTv.voteAverage, tvEntity.voteAverage,0.0)
        assertEquals(dummyTv.popularity, tvEntity.popularity,0.0)
    }
}