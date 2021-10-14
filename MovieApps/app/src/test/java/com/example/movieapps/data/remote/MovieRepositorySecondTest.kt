package com.example.movieapps.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.academies.utils.PagedListUtil
import com.example.movieapps.data.local.LocalDataSource
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.local.entity.Tv
import com.example.movieapps.utils.AppExecutors
import com.example.movieapps.utils.DataDummy
import com.example.movieapps.utils.LiveDataTestUtil
import com.example.movieapps.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositorySecondTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieRepositorySecond = FakeMovieRepository(remote, local, appExecutors)

    private var dummyMovies = DataDummy.generateDummyMovies()
    private val detailMovie = dummyMovies[0]
    private var movieId = dummyMovies[0].id.toString()

    private var dummyTvs = DataDummy.generateTvs()
    private val detailTv = dummyTvs[0]
    private var tvId = dummyTvs[0].id.toString()

    @Test
    fun getMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        movieRepositorySecond.getMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getMovies()
        assertNotNull(movieEntities.data)
        assertEquals(dummyMovies.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTvs() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Tv>
        `when`(local.getTvs()).thenReturn(dataSourceFactory)
        movieRepositorySecond.getTvs()

        val tvEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTvs()))
        verify(local).getTvs()
        assertNotNull(tvEntities.data)
        assertEquals(dummyMovies.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getMoviesBookmarked() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
        `when`(local.getMoviesBookmarked()).thenReturn(dataSourceFactory)
        movieRepositorySecond.getMoviesBookmarked()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getMoviesBookmarked()
        assertNotNull(movieEntities.data)
        assertEquals(dummyMovies.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTvsBookmarked() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Tv>
        `when`(local.getTvsBookmarked()).thenReturn(dataSourceFactory)
        movieRepositorySecond.getTvsBookmarked()

        val tvEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTvs()))
        verify(local).getTvsBookmarked()
        assertNotNull(tvEntities.data)
        assertEquals(dummyMovies.size.toLong(), tvEntities.data?.size?.toLong())
    }


    @Test
    fun getDetailMovie() {
        val detailDummy = MutableLiveData<Movie>()
        detailDummy.value = DataDummy.generateDummyMovies()[0]
        `when`(local.getMovieById(movieId)).thenReturn(detailDummy)

        val movieEntities = LiveDataTestUtil.getValue(movieRepositorySecond.getDetailMovie(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(movieEntities.data)
        assertEquals(detailMovie.title, movieEntities.data?.title)
        assertEquals(detailMovie.overview, movieEntities.data?.overview)
        assertEquals(detailMovie.popularity, movieEntities.data?.popularity)
        assertEquals(detailMovie.vote_count, movieEntities.data?.vote_count)
        assertEquals(detailMovie.vote_average, movieEntities.data?.vote_average)
        assertEquals(detailMovie.release_date, movieEntities.data?.release_date)
    }

    @Test
    fun getDetailTv() {
        val detailDummy = MutableLiveData<Tv>()
        detailDummy.value = DataDummy.generateTvs()[0]
        `when`(local.getTvById(tvId)).thenReturn(detailDummy)

        val tvEntities = LiveDataTestUtil.getValue(movieRepositorySecond.getDetailTv(tvId))
        verify(local).getTvById(tvId)
        assertNotNull(tvEntities.data)
        assertEquals(detailTv.name, tvEntities.data?.name)
        assertEquals(detailTv.overview, tvEntities.data?.overview)
        assertEquals(detailTv.first_air_date, tvEntities.data?.first_air_date)
        assertEquals(detailTv.popularity, tvEntities.data?.popularity)
        assertEquals(detailTv.vote_average, tvEntities.data?.vote_average)
        assertEquals(detailTv.vote_count, tvEntities.data?.vote_count)
        assertEquals(detailTv.poster_path, tvEntities.data?.poster_path)
    }

}