package com.example.macropay.ui.movies

import com.example.macropay.domain.model.Movies

sealed class MoviesUiState {
    data object Loading : MoviesUiState()
    data class Success(val movies: Movies) : MoviesUiState()
    data class Error(val error: Throwable) : MoviesUiState()
}
