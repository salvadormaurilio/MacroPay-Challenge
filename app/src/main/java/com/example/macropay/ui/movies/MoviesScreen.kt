package com.example.macropay.ui.movies

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.macropay.R
import com.example.macropay.core.extensions.empty
import com.example.macropay.data.datasource.exception.DataException
import com.example.macropay.ui.composable.CircularProgressIndicatorFixMax
import com.example.macropay.ui.composable.LaunchSnackbar
import com.example.macropay.ui.composable.SnackbarBlue
import com.example.macropay.ui.theme.MacroPayChallengeTheme
import com.example.macropay.ui.theme.White800


@Composable
fun MoviesScreen(moviesUiState: MoviesUiState, onMovieClick: (id: Int) -> Unit, onLogoutClick: () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(topBar = { MoviesTopAppBar(onLogoutClick = onLogoutClick) },
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
fun MoviesTopAppBar(onLogoutClick: () -> Unit) {
    var mDisplayMenu by remember { mutableStateOf(false) }
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        title = {
            Text(
                text = stringResource(id = R.string.movies),
                color = White800
            )
        },
        actions = {
            IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = String.empty(),
                    tint = White800
                )
            }
            DropdownMenu(
                expanded = mDisplayMenu,
                onDismissRequest = { mDisplayMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.logout)) },
                    onClick = { onLogoutClick() })
            }
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
    MacroPayChallengeTheme {
        MoviesScreen(
            moviesUiState = MoviesUiState.Loading,
            onMovieClick = {},
            onLogoutClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenUiStateSuccessPreview() {
    MacroPayChallengeTheme {
        MoviesScreen(
            moviesUiState = MoviesUiState.Success(givenMovies()),
            onMovieClick = {},
            onLogoutClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenUiStateErrorPreview() {
    MacroPayChallengeTheme {
        MoviesScreen(
            moviesUiState = MoviesUiState.Error(DataException.MoviesException()),
            onMovieClick = {},
            onLogoutClick = {}
        )
    }
}
