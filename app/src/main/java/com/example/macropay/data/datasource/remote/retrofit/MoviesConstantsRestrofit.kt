package com.example.macropay.data.datasource.remote.retrofit

const val AUTHENTICATION_HEADER = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjMDgyMzkzNDQzODA3NWQ2M2YxZGJkYTQwMjNlNzZmYyIsInN1YiI6IjY1MDBmNzJkNTU0NWNhMDExYmE2N2RkYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4QxbpZq9Tj3uzhA8uv2qLNcCA7NIcGBHDzoC4bWv9t8"

const val MOVIE_ID_PATH = "movie_id"

const val MOVIES_ENDPOINT = "3/movie/now_playing?language=es"
const val MOVIE_DETAIL_ENDPOINT = "3/movie/{$MOVIE_ID_PATH}?language=es"

