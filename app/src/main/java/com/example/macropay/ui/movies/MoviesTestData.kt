package com.example.macropay.ui.movies

import com.example.macropay.domain.model.Movie

const val ANY_MOVIE_ID_1 = 823464
const val ANY_MOVIE_TITLE_1 = "Godzilla y Kong: El nuevo imperio"
const val ANY_MOVIE_IMAGE_1 = "https://image.tmdb.org/t/p/w300/2YqZ6IyFk7menirwziJvfoVvSOh.jpg"
const val ANY_MOVIE_RATING_1 = 7.25

const val ANY_MOVIE_ID_2 = 653346
const val ANY_MOVIE_TITLE_2 = "El reino del planeta de los simios"
const val ANY_MOVIE_IMAGE_2 = "https://image.tmdb.org/t/p/w300/kkFn3KM47Qq4Wjhd8GuFfe3LX27.jpg"
const val ANY_MOVIE_RATING_2 = 7.141

const val ANY_MOVIE_ID_3 = 786892
const val ANY_MOVIE_TITLE_3 = "Furiosa: De la saga Mad Max"
const val ANY_MOVIE_IMAGE_3 = "https://image.tmdb.org/t/p/w300/txUUoowOD2MrGXAtI3pWifLR9p6.jpg"
const val ANY_MOVIE_RATING_3 = 7.6

fun givenMovies() = listOf(givenMovie1(), givenMovie2(), givenMovie3())

fun givenMovie1() = Movie(
    id = ANY_MOVIE_ID_1,
    title = ANY_MOVIE_TITLE_1,
    image = ANY_MOVIE_IMAGE_1,
    rating = ANY_MOVIE_RATING_1
)

fun givenMovie2() = Movie(
    id = ANY_MOVIE_ID_2,
    title = ANY_MOVIE_TITLE_2,
    image = ANY_MOVIE_IMAGE_2,
    rating = ANY_MOVIE_RATING_2
)

fun givenMovie3() = Movie(
    id = ANY_MOVIE_ID_3,
    title = ANY_MOVIE_TITLE_3,
    image = ANY_MOVIE_IMAGE_3,
    rating = ANY_MOVIE_RATING_3
)
