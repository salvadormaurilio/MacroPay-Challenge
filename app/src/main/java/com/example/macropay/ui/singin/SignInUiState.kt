package com.example.macropay.ui.singin

sealed class SignInUiState {
    data object Loading : SignInUiState()
    data object Success : SignInUiState()
    data class Error(val error: Throwable) : SignInUiState()
}
