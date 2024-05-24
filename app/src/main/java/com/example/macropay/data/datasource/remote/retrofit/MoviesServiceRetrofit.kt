package com.example.macropay.data.datasource.remote.retrofit

import com.example.macropay.data.datasource.model.MovieDetailResponse
import com.example.macropay.data.datasource.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesServiceRetrofit {

    @GET(MOVIES_ENDPOINT)
    suspend fun fetchMovies(): MoviesResponse

    @GET(MOVIE_DETAIL_ENDPOINT)
    suspend fun fetchMovieDetail(@Path(MOVIE_ID_PATH) movementId: Int): MovieDetailResponse
}
