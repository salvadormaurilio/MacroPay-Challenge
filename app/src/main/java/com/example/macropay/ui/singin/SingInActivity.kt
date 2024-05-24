package com.example.macropay.ui.singin

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
import com.example.macropay.ui.movies.MoviesActivity
import com.example.macropay.ui.theme.MicroPayChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SingInActivity : ComponentActivity() {

    private val singInViewModel by viewModels<SingInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContent()
        collectSingInActions()
        singInViewModel.validSession()
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
        val signInUiState by singInViewModel.signInUiState.collectAsState()

        SigInScreen(
            signInUiState = signInUiState,
            onSignInButtonClick = { email, password -> singInViewModel.signIn(email, password) },
            onSingInSuccess = { singInViewModel.navigateToHome() },
        )
    }

    private fun collectSingInActions() {
        lifecycleScope.launch {
            singInViewModel.navigateToHome
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collect { openHomeActivity() }
        }
    }

    private fun openHomeActivity() {
        startActivity(intentToAndClearStack<MoviesActivity>())
    }
}


