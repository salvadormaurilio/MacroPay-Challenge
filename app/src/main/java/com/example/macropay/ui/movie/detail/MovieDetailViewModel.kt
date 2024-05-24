package com.example.macropay.ui.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.macropay.core.coroutines.CoroutinesDispatchers
import com.example.macropay.domain.GetMovieDetailUseCase
import com.example.macropay.domain.model.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _movieDetailUiState = MutableStateFlow<MovieDetailUiState?>(null)

    val movieDetailUiState: StateFlow<MovieDetailUiState?>
        get() = _movieDetailUiState

    fun getMovieDetail(movieId: Int) = viewModelScope.launch(coroutinesDispatchers.io) {
        emitMovieDetailUiState(MovieDetailUiState.Loading)

        getMovieDetailUseCase.fetchMovieDetail(movieId).collect {
            getMovieDetailSuccess(it)
            getMovieDetailError(it)
        }
    }

    private fun getMovieDetailSuccess(result: Result<MovieDetail>) = result.onSuccess {
        emitMovieDetailUiState(MovieDetailUiState.Success(it))
    }

    private fun getMovieDetailError(result: Result<MovieDetail>) = result.onFailure {
        it.printStackTrace()
        emitMovieDetailUiState(MovieDetailUiState.Error(it))
    }

    private fun emitMovieDetailUiState(movieDetailUiState: MovieDetailUiState) {
        _movieDetailUiState.value = movieDetailUiState
    }

}
