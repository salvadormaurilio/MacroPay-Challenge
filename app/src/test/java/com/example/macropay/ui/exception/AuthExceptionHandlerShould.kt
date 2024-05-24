package com.example.macropay.ui.exception

import com.example.macropay.core.assertThatEquals
import com.example.macropay.fakedata.ANY_INVALID_PASSWORD
import com.example.macropay.fakedata.ANY_INVALID_USER_EMAIL
import com.example.macropay.fakedata.ANY_PASSWORD
import com.example.macropay.fakedata.ANY_USER_EMAIL
import org.junit.Before
import org.junit.Test

class AuthExceptionHandlerShould {

    private lateinit var authExceptionHandler: AuthExceptionHandler

    @Before
    fun setup() {
        authExceptionHandler = AuthExceptionHandler()
    }

    @Test
    fun `return NoValidationNeededException when areInvalidSingInCredentials is called and email and password are valid`() {
        val result = authExceptionHandler.areInvalidSingInCredentials(ANY_USER_EMAIL, ANY_PASSWORD)

        assertThatEquals(result.second, AuthUiException.NoValidationNeededException)
        assertThatEquals(result.first, false)
    }

    @Test
    fun `return EmailException when areInvalidSingInCredentials is called and email is valid`() {
        val result = authExceptionHandler.areInvalidSingInCredentials(ANY_INVALID_USER_EMAIL, ANY_PASSWORD)

        assertThatEquals(result.second, AuthUiException.EmailException)
        assertThatEquals(result.first, true)
    }

    @Test
    fun `return PasswordException when areInvalidSingInCredentials is called and password is valid`() {
        val result = authExceptionHandler.areInvalidSingInCredentials(ANY_USER_EMAIL, ANY_INVALID_PASSWORD)

        assertThatEquals(result.second, AuthUiException.PasswordException)
        assertThatEquals(result.first, true)
    }
}
