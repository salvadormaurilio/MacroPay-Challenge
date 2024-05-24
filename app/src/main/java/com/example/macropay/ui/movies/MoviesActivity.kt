package com.example.macropay.ui.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.macropay.core.extensions.intentToAndClearStack
import com.example.macropay.ui.movie.detail.MovieDetailDetailActivity
import com.example.macropay.ui.singin.SingInActivity
import com.example.macropay.ui.theme.MacroPayChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {

    private val moviesViewModel by viewModels<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContent()
        collectMovementDetailAction()
        moviesViewModel.getMovies()

    }

    private fun initContent() {
        setContent {
            MacroPayChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InitContentWithUiState()
                }
            }
        }
    }

    @Composable
    private fun InitContentWithUiState() {
        val moviesUiState by moviesViewModel.moviesUiState.collectAsState()
        moviesUiState?.run {
            MoviesScreen(
                moviesUiState = this,
                onMovieClick = { moviesViewModel.navigateToMovieDetail(it) },
                onLogoutClick = {moviesViewModel.logOut()}
            )
        }
    }

    private fun collectMovementDetailAction() {
        lifecycleScope.launch {
            moviesViewModel.navigateToMovieDetail
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collect { openMovieDetailActivity(it) }
        }

        lifecycleScope.launch {
            moviesViewModel.navigateToSignIn
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collect { openSignInActivity() }
        }
    }

    private fun openMovieDetailActivity(id: Int) {
        startActivity(MovieDetailDetailActivity.intentToMovieDetailActivity(this, id))
    }

    private fun openSignInActivity() {
        startActivity(intentToAndClearStack<SingInActivity>())
    }
}
