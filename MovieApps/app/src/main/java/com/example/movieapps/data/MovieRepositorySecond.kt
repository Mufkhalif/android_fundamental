package com.example.movieapps.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.local.entity.Tv
import com.example.movieapps.api.response.ResponseMovie
import com.example.movieapps.api.response.ResponseTv
import com.example.movieapps.data.local.LocalDataSource
import com.example.movieapps.data.remote.ApiResponse
import com.example.movieapps.data.remote.RemoteDataSource
import com.example.movieapps.utils.AppExecutors
import com.example.movieapps.vo.Resource

class MovieRepositorySecond private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MovieDataSource {
    companion object {
        @Volatile
        private var instance: MovieRepositorySecond? = null
        private val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepositorySecond =
            instance ?: synchronized(this) {
                MovieRepositorySecond(
                    remoteDataSource,
                    localDataSource,
                    appExecutors
                ).apply { instance = this }
            }
    }

    override fun getMovies(): LiveData<Resource<PagedList<Movie>>> {
        return object :
            NetworkBoundResource<PagedList<Movie>, ResponseMovie>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<Movie>> {
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<ResponseMovie>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: ResponseMovie) {
                val movies = ArrayList<Movie>()
                for (response in data.results) {
                    val movie = Movie(
                        response.id,
                        response.title,
                        response.overview,
                        response.release_date,
                        response.popularity,
                        response.vote_average,
                        response.vote_count,
                        response.poster_path,
                        false
                    )
                    movies.add(movie)
                }
                localDataSource.insertMovies(data.results)
            }
        }.asLiveData()
    }

    override fun getTvs(): LiveData<Resource<PagedList<Tv>>> {
        return object : NetworkBoundResource<PagedList<Tv>, ResponseTv>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<Tv>> {
                return LivePagedListBuilder(localDataSource.getTvs(), config).build()
            }

            override fun shouldFetch(data: PagedList<Tv>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<ResponseTv>> = remoteDataSource.getTvs()

            override fun saveCallResult(data: ResponseTv) {
                val tvs = ArrayList<Tv>()
                for (response in data.results) {
                    val tv = Tv(
                        response.id,
                        response.name,
                        response.overview,
                        response.first_air_date,
                        response.popularity,
                        response.vote_average,
                        response.vote_count,
                        response.poster_path,
                        false
                    )
                    tvs.add(tv)
                }
                localDataSource.insertTvs(tvs)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(id: String): LiveData<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, Movie>(appExecutors) {
            override fun loadFromDb(): LiveData<Movie> = localDataSource.getMovieById(id)

            override fun shouldFetch(data: Movie?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<Movie>> =
                remoteDataSource.getDetailMovie(id)

            override fun saveCallResult(data: Movie) {
                localDataSource.insertMovie(data)
            }
        }.asLiveData()
    }


    override fun getDetailTv(id: String): LiveData<Resource<Tv>> {
        return object : NetworkBoundResource<Tv, Tv>(appExecutors) {
            override fun shouldFetch(data: Tv?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<Tv>> = remoteDataSource.getDetailTv(id)

            override fun saveCallResult(data: Tv) {
                localDataSource.insertTv(data)
            }

            override fun loadFromDb(): LiveData<Tv> = localDataSource.getTvById(id)
        }.asLiveData()
    }

    override fun getMoviesBookmarked(): LiveData<PagedList<Movie>> {
        return LivePagedListBuilder(localDataSource.getMoviesBookmarked(), config).build()
    }

    override fun setMovieBookmark(movie: Movie, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieBookmark(movie, state) }

    override fun getTvsBookmarked(): LiveData<PagedList<Tv>> {
        return LivePagedListBuilder(localDataSource.getTvsBookmarked(), config).build()
    }

    override fun setTvBookmark(tv: Tv, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setTvBookmark(tv, state) }
    }

}