package com.example.macropay.ui.movies

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.macropay.R
import com.example.macropay.data.datasource.exception.DataException
import com.example.macropay.ui.composable.CircularProgressIndicatorFixMax
import com.example.macropay.ui.composable.LaunchSnackbar
import com.example.macropay.ui.composable.SnackbarBlue
import com.example.macropay.ui.theme.MicroPayChallengeTheme
import com.example.macropay.ui.theme.White800


@Composable
fun MoviesScreen(moviesUiState: MoviesUiState, onMovieClick: (id: Int) -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(topBar = { MoviesTopAppBar() },
        snackbarHost = { SnackbarBlue(snackbarHostState = snackbarHostState) }) {
        MoviesUiState(
            moviesUiState = moviesUiState,
            paddingValues = it,
            snackbarHostState = snackbarHostState,
            onMovieClick = onMovieClick
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MoviesTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        title = {
            Text(
                text = stringResource(id = R.string.movies),
                color = White800
            )
        }
    )
}

@Composable
private fun MoviesUiState(
    moviesUiState: MoviesUiState,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    onMovieClick: (id: Int) -> Unit
) {
    when (moviesUiState) {
        is MoviesUiState.Loading -> {
            CircularProgressIndicatorFixMax()
        }

        is MoviesUiState.Success -> {
            MovieItems(
                modifier = Modifier.padding(paddingValues = paddingValues),
                movies = moviesUiState.movies,
                onMovieClick = onMovieClick
            )
        }

        is MoviesUiState.Error -> {
            LaunchSnackbar(snackbarHostState = snackbarHostState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenUiStateLoadingPreview() {
    MicroPayChallengeTheme {
        MoviesScreen(
            moviesUiState = MoviesUiState.Loading,
            onMovieClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenUiStateSuccessPreview() {
    MicroPayChallengeTheme {
        MoviesScreen(
            moviesUiState = MoviesUiState.Success(givenMovies()),
            onMovieClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenUiStateErrorPreview() {
    MicroPayChallengeTheme {
        MoviesScreen(
            moviesUiState = MoviesUiState.Error(DataException.MoviesException()),
            onMovieClick = {}
        )
    }
}