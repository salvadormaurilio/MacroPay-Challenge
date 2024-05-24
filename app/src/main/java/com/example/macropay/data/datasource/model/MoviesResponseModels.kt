package com.example.macropay.data.datasource.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val dates: MoviesDatesResponse,
    val page: Long,
    @SerializedName("results")
    val results: List<MoviesResultResponse>,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long,
)

data class MoviesDatesResponse(
    val maximum: String,
    val minimum: String,
)

data class MoviesResultResponse(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Long>,
    val id: Long,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Long,
)
