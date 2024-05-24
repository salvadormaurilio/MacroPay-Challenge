package com.example.macropay.ui.singin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.macropay.R
import com.example.macropay.core.extensions.empty
import com.example.macropay.data.datasource.exception.DataException
import com.example.macropay.ui.composable.EmailTextField
import com.example.macropay.ui.composable.LaunchSnackbar
import com.example.macropay.ui.composable.PasswordTextField
import com.example.macropay.ui.composable.ProgressButton
import com.example.macropay.ui.composable.SnackbarBlue
import com.example.macropay.ui.exception.AuthUiException
import com.example.macropay.ui.theme.MacroPayChallengeTheme
import com.example.macropay.ui.theme.Space16
import com.example.macropay.ui.theme.Space32
import com.example.macropay.ui.theme.White800

@Composable
fun SigInScreen(
    modifier: Modifier = Modifier,
    signInUiState: SignInUiState? = null,
    onSignInButtonClick: (email: String, password: String) -> Unit,
    onSingInSuccess: () -> Unit
) {
    if (signInUiState is SignInUiState.Success) onSingInSuccess()
    val isLoading = signInUiState is SignInUiState.Loading
    val errorException = (signInUiState as? SignInUiState.Error)?.error

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { SigInTopAppBar() },
        snackbarHost = { SnackbarBlue(snackbarHostState) }) {
        SigInContent(
            modifier = modifier.padding(paddingValues = it),
            isLoading = isLoading,
            errorException = errorException,
            onSignInButtonClick = onSignInButtonClick,
        )
        SnackbarError(errorException, snackbarHostState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SigInTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        title = {
            Text(
                text = stringResource(id = R.string.sign_in),
                color = White800
            )
        }
    )
}

@Composable
fun SigInContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorException: Throwable?,
    onSignInButtonClick: (email: String, password: String) -> Unit
) {

    val emailError = (errorException as? AuthUiException.EmailException)?.error
    val passwordError = (errorException as? AuthUiException.PasswordException)?.error

    var email by rememberSaveable { mutableStateOf(String.empty()) }
    var password by rememberSaveable { mutableStateOf(String.empty()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = Space32),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailTextField(
            email = email,
            error = emailError,
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(Space16))
        PasswordTextField(
            password = password,
            error = passwordError,
            onValueChange = { password = it }
        )
        Spacer(modifier = Modifier.height(Space32))
        ProgressButton(
            isLoading = isLoading,
            text = R.string.sign_in,
            onClick = { onSignInButtonClick(email, password) }
        )
    }
}

@Composable
private fun SnackbarError(errorException: Throwable?, snackbarHostState: SnackbarHostState) {
    if (errorException != null && errorException !is AuthUiException) {
        LaunchSnackbar(snackbarHostState = snackbarHostState, message = getMessageError(errorException))
    }
}

@Composable
private fun getMessageError(errorException: Throwable) = when (errorException) {
    is DataException.SignInException -> stringResource(id = R.string.error_sign_in)
    else -> errorException.message.orEmpty()
}

@Preview(showBackground = true)
@Composable
fun SingInScreenPreview() {
    MacroPayChallengeTheme {
        SigInScreen(
            onSignInButtonClick = { _, _ -> },
            onSingInSuccess = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingInScreenUiStateLoadingPreview() {
    MacroPayChallengeTheme {
        SigInScreen(
            signInUiState = SignInUiState.Loading,
            onSignInButtonClick = { _, _ -> },
            onSingInSuccess = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingInScreenEmailUiStateErrorPreview() {
    MacroPayChallengeTheme {
        SigInScreen(
            signInUiState = SignInUiState.Error(AuthUiException.EmailException),
            onSignInButtonClick = { _, _ -> },
            onSingInSuccess = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingInScreenPasswordUiStateErrorPreview() {
    MacroPayChallengeTheme {
        SigInScreen(
            signInUiState = SignInUiState.Error(AuthUiException.PasswordException),
            onSignInButtonClick = { _, _ -> },
            onSingInSuccess = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SingInScreenOtherUiStateErrorPreview() {
    MacroPayChallengeTheme {
        SigInScreen(
            signInUiState = SignInUiState.Error(DataException.SignInException()),
            onSignInButtonClick = { _, _ -> },
            onSingInSuccess = {}
        )
    }
}
