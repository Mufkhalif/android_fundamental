package com.example.movieapps.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapps.api.model.Status
import com.example.movieapps.api.response.ResponseMovie
import com.example.movieapps.api.response.ResponseTv
import com.example.movieapps.utils.DataDummy
import com.example.movieapps.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieRepositorySecondTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepositorySecond = FakeMovieRepository(remote)

    private var dummyMovies = DataDummy.generateDummyMovies()
    private val dummyResponseMovie = ResponseMovie(
        10,
        dummyMovies,
        20
    )
    private val detailMovie = DataDummy.generateDummyMovies()[0]

    private var dummyTvs = DataDummy.generateTvs()
    private val dummyResponseTv = ResponseTv(
        10,
        dummyTvs,
        20,
        Status(true)
    )
    private val detailTv = DataDummy.generateTvs()[0]

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(dummyResponseMovie)
            null
        }.`when`(remote).getMovies(any())

        val moviesEntities = LiveDataTestUtil.getValue(movieRepositorySecond.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(moviesEntities)

        assertEquals(dummyResponseMovie.page, moviesEntities.page)
        assertEquals(dummyResponseMovie.results.size.toLong(), moviesEntities.results.size.toLong())
        assertEquals(dummyResponseMovie.total_pages, moviesEntities.total_pages)
    }

    @Test
    fun getTvs() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvCallback)
                .onAllTvReceived(dummyResponseTv)
        }.`when`(remote).getTvs(any())

        val tvEntities = LiveDataTestUtil.getValue(movieRepositorySecond.getTvs())
        verify(remote).getTvs(any())
        assertNotNull(tvEntities)

        assertEquals(dummyResponseTv.page, tvEntities.page)
        assertEquals(dummyResponseTv.results.size.toLong(), tvEntities.results.size.toLong())
        assertEquals(dummyResponseTv.total_results, tvEntities.total_results)
        assertEquals(dummyResponseTv.success, tvEntities.success)
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailMovieCallback)
                .onDetailMovieReceived(detailMovie)
        }.`when`(remote).getDetailMovie(eq(detailMovie.id.toString()), any())

        val detailEntities =
            LiveDataTestUtil.getValue(movieRepositorySecond.getDetailMovie(detailMovie.id.toString()))
        verify(remote).getDetailMovie(eq(detailMovie.id.toString()), any())
        assertNotNull(detailEntities)

        assertEquals(detailMovie.title, detailEntities.title)
        assertEquals(detailMovie.overview, detailEntities.overview)
        assertEquals(detailMovie.popularity, detailEntities.popularity, 0.0)
        assertEquals(detailMovie.vote_count, detailEntities.vote_count)
        assertEquals(detailMovie.vote_average, detailEntities.vote_average, 0.0)
        assertEquals(detailMovie.release_date, detailEntities.release_date)
    }

    @Test
    fun getDetailTv() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailTvCallback)
                .onDetailTvCallback(detailTv)
        }.`when`(remote).getDetailTv(eq(detailTv.id.toString()), any())

        val detailEntities =
            LiveDataTestUtil.getValue(movieRepositorySecond.getDetailTv(detailTv.id.toString()))
        verify(remote).getDetailTv(eq(detailTv.id.toString()), any())
        assertNotNull(detailEntities)

        assertEquals(detailTv.name, detailEntities.name)
        assertEquals(detailTv.overview, detailEntities.overview)
        assertEquals(detailTv.first_air_date, detailEntities.first_air_date)
        assertEquals(detailTv.popularity.toString(), detailEntities.popularity.toString())
        assertEquals(detailTv.vote_average.toString(), detailEntities.vote_average.toString())
        assertEquals(detailTv.vote_count, detailEntities.vote_count)
        assertEquals(detailTv.poster_path, detailEntities.poster_path)
    }

}