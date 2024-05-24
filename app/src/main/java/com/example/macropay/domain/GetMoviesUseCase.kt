package com.example.macropay.domain

import com.example.macropay.data.MoviesRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    fun fetchMovies() = moviesRepository.fetchMovies()
}
