package com.example.macropay.data.datasource.exception

sealed class AuthException(message: String) : Exception(message) {
    data class SignInException(override val message: String = "Some error happened with the Sign In") : Exception(message)
}
