package com.example.macropay.data

import com.example.macropay.data.datasource.model.toMovieDetailResponse
import com.example.macropay.data.datasource.model.toMovies
import com.example.macropay.data.datasource.remote.MoviesRemoteDataSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val moviesRemoteDataSource: MoviesRemoteDataSource) {

    fun fetchMovies() = moviesRemoteDataSource.fetchMovies().map { it.toMovies() }

    fun fetchMovieDetail(movieId: Int) = moviesRemoteDataSource.fetchMovieDetail(movieId).map { it.toMovieDetailResponse() }
}
