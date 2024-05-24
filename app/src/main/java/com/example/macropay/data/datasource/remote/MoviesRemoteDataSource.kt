package com.example.macropay.data.datasource.remote

import com.example.macropay.data.datasource.exception.DataException
import com.example.macropay.data.datasource.model.MoviesResponse
import com.example.macropay.data.datasource.remote.retrofit.MoviesServiceRetrofit
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(private val moviesServiceRetrofit: MoviesServiceRetrofit) {

    fun fetchMovies() = flow {
        try {
            val moviesResponse = moviesServiceRetrofit.fetchMovies()
            emit(Result.success(moviesResponse))
        } catch (exception: Exception) {
            emit(Result.failure(DataException.MoviesException()))
        }
    }

    fun fetchMovieDetail(movieId: Int) = flow {
        try {
            val moviesResponse = moviesServiceRetrofit.fetchMovieDetail(movieId)
            emit(Result.success(moviesResponse))
        } catch (exception: Exception) {
            emit(Result.failure(DataException.MoviesException()))
        }
    }
}
