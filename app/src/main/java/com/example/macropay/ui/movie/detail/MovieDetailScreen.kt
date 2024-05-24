package com.example.macropay.ui.movie.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.macropay.R
import com.example.macropay.core.extensions.empty
import com.example.macropay.data.datasource.exception.DataException
import com.example.macropay.domain.model.MovieDetail
import com.example.macropay.ui.composable.CircularProgressIndicatorFixMax
import com.example.macropay.ui.composable.LaunchSnackbar
import com.example.macropay.ui.composable.SnackbarBlue
import com.example.macropay.ui.theme.MacroPayChallengeTheme
import com.example.macropay.ui.theme.Space12
import com.example.macropay.ui.theme.Space16
import com.example.macropay.ui.theme.White800

@Composable
fun MovieDetailScreen(movieDetailUiState: MovieDetailUiState, onBackPressedClick: () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { MovementDetailTopAppBar(onBackPressedClick = onBackPressedClick) },
        snackbarHost = { SnackbarBlue(snackbarHostState = snackbarHostState) }) {
        MovementDetailUiState(
            movieDetailUiState = movieDetailUiState,
            paddingValues = it, snackbarHostState = snackbarHostState
        )
    }
}

@Composable
private fun MovementDetailUiState(
    movieDetailUiState: MovieDetailUiState,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    when (movieDetailUiState) {
        is MovieDetailUiState.Loading -> {
            CircularProgressIndicatorFixMax()
        }

        is MovieDetailUiState.Success -> {
            MovementDetailContent(
                modifier = Modifier.padding(paddingValues = paddingValues),
                movieDetail = movieDetailUiState.movieDetail
            )
        }

        is MovieDetailUiState.Error -> {
            LaunchSnackbar(snackbarHostState = snackbarHostState)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MovementDetailTopAppBar(onBackPressedClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        title = {
            Text(
                text = stringResource(id = R.string.movie_detail),
                color = White800
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressedClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = String.empty(),
                    tint = White800
                )
            }
        }
    )
}

@Composable
fun MovementDetailContent(modifier: Modifier = Modifier, movieDetail: MovieDetail) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            model = movieDetail.image,
            contentDescription = String.empty()
        )
        Spacer(modifier = Modifier.height(Space16))
        Text(
            modifier = Modifier.padding(horizontal = Space16),
            text = movieDetail.title,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(Space12))
        Text(
            modifier = Modifier
                .padding(horizontal = Space16),
            text = movieDetail.description,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(Space12))
        MovieDetailLabel(
            title = R.string.duration,
            text = stringResource(id = R.string.duration_with_value, movieDetail.duration)
        )
        MovieDetailLabel(
            title = R.string.release_date,
            text = movieDetail.releaseDate
        )
        MovieDetailLabel(
            title = R.string.rating,
            text = movieDetail.rating
        )
        MovieDetailLabel(
            title = R.string.genres,
            text = movieDetail.genres
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovementDetailScreenUiStateLoadingPreview() {
    MacroPayChallengeTheme {
        MovieDetailScreen(
            movieDetailUiState = MovieDetailUiState.Loading,
            onBackPressedClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovementDetailScreenUiStateSuccessPreview() {
    MacroPayChallengeTheme {
        MovieDetailScreen(
            movieDetailUiState = MovieDetailUiState.Success(givenMovieDetail()),
            onBackPressedClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovementDetailScreenUiStateErrorPreview() {
    MacroPayChallengeTheme {
        MovieDetailScreen(
            movieDetailUiState = MovieDetailUiState.Error(DataException.MovieDetailException()),
            onBackPressedClick = {}
        )
    }
}
