package com.example.macropay.domain

import com.example.macropay.data.MoviesRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    fun fetchMovieDetail(movieId: Int) = moviesRepository.fetchMovieDetail(movieId)
}
