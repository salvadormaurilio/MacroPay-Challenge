package com.example.macropay.ui.exception

import com.example.macropay.core.ui.isValidEmail
import com.example.macropay.core.ui.isValidPassword
import javax.inject.Inject
import com.example.macropay.ui.exception.AuthUiException.EmailException
import com.example.macropay.ui.exception.AuthUiException.NoValidationNeededException
import com.example.macropay.ui.exception.AuthUiException.PasswordException

class AuthExceptionHandler @Inject constructor() {

    fun areInvalidSingInCredentials(email: String, password: String) = when {
        !email.isValidEmail() -> Pair(true, EmailException)
        !password.isValidPassword() -> Pair(true, PasswordException)
        else -> Pair(false, NoValidationNeededException)
    }


    companion object {
        private const val MIN_CHARACTERS_NAMES = 4
    }
}
