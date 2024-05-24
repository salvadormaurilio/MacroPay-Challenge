package com.example.macropay.domain.model

data class MovieDetail(
    val id: Int,
    val image: String,
    val title: String,
    val duration: Int,
    val releaseDate: String,
    val rating: Double,
    val genres: List<String>,
    val description: String
)