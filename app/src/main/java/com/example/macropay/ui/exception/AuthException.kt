package com.example.macropay.ui.exception

import androidx.annotation.StringRes
import com.example.macropay.R

sealed class AuthUiException(@StringRes val error: Int? = null) : Exception() {
    data object EmailException : AuthUiException(R.string.error_email_invalid)
    data object PasswordException : AuthUiException(R.string.error_password_invalid)
    data object NoValidationNeededException : AuthUiException()
}
