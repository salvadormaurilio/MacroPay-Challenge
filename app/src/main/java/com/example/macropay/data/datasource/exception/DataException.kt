package com.example.macropay.data.datasource.exception

sealed class DataException(message: String) : Exception(message) {
    data class SignInException(override val message: String = "Some error happened with the Sign In") : Exception(message)
    data class MoviesException(override val message: String = "Some error happened with the get Movies Data") : Exception(message)
    data class MovieDetailException(override val message: String = "Some error happened with the get Movie Data") : Exception(message)
}
