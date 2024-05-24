package com.example.macropay.ui.movie.detail

import com.example.macropay.domain.model.MovieDetail

sealed class MovieDetailUiState {
    data object Loading : MovieDetailUiState()
    data class Success(val movieDetail: MovieDetail) : MovieDetailUiState()
    data class Error(val error: Throwable) : MovieDetailUiState()
}
