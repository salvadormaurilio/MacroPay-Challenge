package com.example.macropay.ui.singin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.macropay.core.coroutines.CoroutinesDispatchers
import com.example.macropay.domain.SignInUseCase
import com.example.macropay.ui.exception.AuthExceptionHandler
import com.example.macropay.ui.singin.SignInUiState.Error
import com.example.macropay.ui.singin.SignInUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SingInViewModel @Inject constructor(
        private val authExceptionHandler: AuthExceptionHandler,
        private val signInUseCase: SignInUseCase,
        private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _signInUiState = MutableStateFlow<SignInUiState?>(null)

    val signInUiState: StateFlow<SignInUiState?>
        get() = _signInUiState

    private val _navigateToHome = MutableSharedFlow<Unit>()

    val navigateToHome: SharedFlow<Unit>
        get() = _navigateToHome

    fun signIn(email: String, password: String) = viewModelScope.launch(coroutinesDispatchers.io) {
        emitSignInUiState(Loading)

        val (areInvalidCredentials, exception) = authExceptionHandler.areInvalidSingInCredentials(email, password)
        if (areInvalidCredentials) return@launch emitSignInUiState(Error(exception))

        signInUseCase.signIn(email, password).collect {
            signInSuccess(it)
            signInError(it)
        }
    }

    private fun signInSuccess(result: Result<String>) = result.onSuccess {
        emitSignInUiState(SignInUiState.Success)
    }

    private fun signInError(result: Result<String>) = result.onFailure {
        it.printStackTrace()
        emitSignInUiState(Error(it))
    }

    private fun emitSignInUiState(signInUiState: SignInUiState) {
        _signInUiState.value = signInUiState
    }

    fun navigateToHome() = viewModelScope.launch {
        _navigateToHome.emit(Unit)
    }
}
