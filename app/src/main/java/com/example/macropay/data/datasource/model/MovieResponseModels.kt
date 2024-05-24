package com.example.macropay.data.datasource.model

import com.example.macropay.core.extensions.orDefault
import com.example.macropay.domain.model.Movie
import com.example.macropay.domain.model.MovieDetail
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: MovieBelongsToCollectionResponse?,
    val budget: Int?,
    val genres: List<MovieGenreResponse>?,
    val homepage: String?,
    val id: Int?,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("origin_country")
    val originCountry: List<String>?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<MovieProductionCompanyResponse?>?,
    @SerializedName("production_countries")
    val productionCountries: List<MovieProductionCountryResponse?>?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<MovieSpokenLanguageResponse?>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
)

data class MovieBelongsToCollectionResponse(
    val id: Int?,
    val name: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
)

data class MovieGenreResponse(
    val id: Int?,
    val name: String?,
)

data class MovieProductionCompanyResponse(
    val id: Int?,
    @SerializedName("logo_path")
    val logoPath: String?,
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?,
)

data class MovieProductionCountryResponse(
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    val name: String?,
)

data class MovieSpokenLanguageResponse(
    @SerializedName("english_name")
    val englishName: String?,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    val name: String?,
)

fun Result<MovieDetailResponse>.toMovieDetailResponse() = map { it.toMovieDetail() }

fun MovieDetailResponse.toMovieDetail() = MovieDetail(
    id = id.orDefault(),
    image =  BASE_MOVIE_URL+backdropPath.orEmpty(),
    title = title.orEmpty(),
    duration = runtime.orDefault(),
    releaseDate = releaseDate.orEmpty(),
    rating = voteAverage.orDefault(),
    genres = genres?.mapNotNull { it.name }.orEmpty(),
    description = overview.orEmpty()
)

private const val BASE_MOVIE_URL= "https://image.tmdb.org/t/p/w500/"