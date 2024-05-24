package com.example.macropay.domain.model

data class MovieDetail(
    val id: Int,
    val image: String,
    val title: String,
    val duration: String,
    val releaseDate: String,
    val rating: Int,
    val gender: String,
    val description: String
)