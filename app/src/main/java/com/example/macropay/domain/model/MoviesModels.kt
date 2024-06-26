package com.example.macropay.domain.model

data class Movies(
    val page: Int,
    val totalPages: Int,
    val movies: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val image: String,
    val rating: Double,
)