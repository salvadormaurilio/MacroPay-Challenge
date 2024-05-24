package com.example.macropay.ui.movies

import com.example.macropay.domain.model.Movie

sealed class MoviesUiState {
    data object Loading : MoviesUiState()
    data class Success(val movies: List<Movie>) : MoviesUiState()
    data class Error(val error: Throwable) : MoviesUiState()
}
