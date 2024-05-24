package com.example.macropay.ui.movie.detail

import android.content.Context
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
import com.example.macropay.core.extensions.intentTo
import com.example.macropay.core.extensions.orDefault
import com.example.macropay.ui.theme.MicroPayChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailDetailActivity : ComponentActivity() {

    private val movieDetailViewModel by viewModels<MovieDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContent()
        movieDetailViewModel.getMovieDetail(getMovieId())
    }

    private fun initContent() {
        setContent {
            MicroPayChallengeTheme {
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
        val movieDetailUiState by movieDetailViewModel.movieDetailUiState.collectAsState()
        movieDetailUiState?.run {
            MovieDetailScreen(
                movieDetailUiState = this,
                onBackPressedClick = { onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    private fun getMovieId() = intent?.getIntExtra(MOVIE_ID, 0).orDefault()

    companion object {
        private const val MOVIE_ID = "movie_id"

        fun intentToMovieDetailActivity(context: Context, movieId: Int) =
            context.intentTo<MovieDetailDetailActivity>()
                .putExtra(MOVIE_ID, movieId)
    }
}
