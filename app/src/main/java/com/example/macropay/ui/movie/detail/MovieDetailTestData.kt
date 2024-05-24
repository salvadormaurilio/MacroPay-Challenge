package com.example.macropay.ui.movie.detail

import com.example.macropay.domain.model.MovieDetail

const val ANY_MOVIE_ID = 823464
const val ANY_MOVIE_IMAGE = "https://image.tmdb.org/t/p/w200/2YqZ6IyFk7menirwziJvfoVvSOh.jpg"
const val ANY_MOVIE_TITLE = "Godzilla y Kong: El nuevo imperio"
const val ANY_MOVIE_DURATION = 115
const val ANY_MOVIE_RELEASE_DATE = "2024-03-27"
const val ANY_MOVIE_RATING = 7.25
const val ANY_MOVIE_GENRE_1 = "Ciencia ficción"
const val ANY_MOVIE_GENRE_2 = "Acción"
const val ANY_MOVIE_GENRE_3 = "Aventura"
const val ANY_MOVIE_DESCRIPTION =
    "Una aventura cinematográfica completamente nueva, que enfrentará al todopoderoso Kong y al temible Godzilla contra una colosal amenaza desconocida escondida dentro de nuestro mundo. La nueva y épica película profundizará en las historias de estos titanes, sus orígenes y los misterios de Isla Calavera y más allá, mientras descubre la batalla mítica que ayudó a forjar a estos seres extraordinarios y los unió a la humanidad para siempre."

fun givenMovieDetail() = MovieDetail(
    id = ANY_MOVIE_ID,
    image = ANY_MOVIE_IMAGE,
    title = ANY_MOVIE_TITLE,
    duration = ANY_MOVIE_DURATION,
    releaseDate = ANY_MOVIE_RELEASE_DATE,
    rating = ANY_MOVIE_RATING,
    genres = listOf(ANY_MOVIE_GENRE_1, ANY_MOVIE_GENRE_2, ANY_MOVIE_GENRE_3),
    description = ANY_MOVIE_DESCRIPTION
)