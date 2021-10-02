package com.example.movieapps.ui.tv

import com.example.movieapps.data.TvEntity
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TvViewModelTest {
    private lateinit var viewModel: TvViewModel

    @Before
    fun setUp() {
        viewModel = TvViewModel()
    }

    @Test
    fun getTv() {
        val tvs = viewModel.getTv()
        assertNotNull(tvs)
        assertEquals(10, tvs.size)
    }

    @Test
    fun getEmptyTv() {
        val tvs = ArrayList<TvEntity>()
        assertEquals(0, tvs.size)
    }
}