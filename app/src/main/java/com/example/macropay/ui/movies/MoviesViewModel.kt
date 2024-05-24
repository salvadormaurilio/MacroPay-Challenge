package com.example.macropay.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.macropay.core.coroutines.CoroutinesDispatchers
import com.example.macropay.domain.GetMoviesUseCase
import com.example.macropay.domain.LogOutUseCase
import com.example.macropay.domain.model.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _moviesUiState = MutableStateFlow<MoviesUiState?>(null)

    val moviesUiState: StateFlow<MoviesUiState?>
        get() = _moviesUiState

    private val _navigateToMovieDetail = MutableSharedFlow<Int>()

    val navigateToMovieDetail: SharedFlow<Int>
        get() = _navigateToMovieDetail

    private val _navigateToSignIn = MutableSharedFlow<Unit>()

    val navigateToSignIn: SharedFlow<Unit>
        get() = _navigateToSignIn

    fun getMovies() = viewModelScope.launch(coroutinesDispatchers.io) {
        emitMoviesUiState(MoviesUiState.Loading)

        getMoviesUseCase.fetchMovies().collect {
            getMoviesSuccess(it)
            getMoviesError(it)
        }
    }

    private fun getMoviesSuccess(result: Result<Movies>) = result.onSuccess {
        emitMoviesUiState(MoviesUiState.Success(it.movies))
    }

    private fun getMoviesError(result: Result<Movies>) = result.onFailure {
        it.printStackTrace()
        emitMoviesUiState(MoviesUiState.Error(it))
    }

    private fun emitMoviesUiState(moviesUiState: MoviesUiState) {
        _moviesUiState.value = moviesUiState
    }

    fun navigateToMovieDetail(movieId: Int) = viewModelScope.launch {
        _navigateToMovieDetail.emit(movieId)
    }

    fun logOut() = viewModelScope.launch(coroutinesDispatchers.io){
        logOutUseCase.logOut()
        withContext(coroutinesDispatchers.main){
            _navigateToSignIn.emit(Unit)
        }
    }
}
