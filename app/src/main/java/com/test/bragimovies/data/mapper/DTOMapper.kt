package com.test.bragimovies.data.mapper

import com.test.bragimovies.data.model.GenreDto
import com.test.bragimovies.data.model.MovieDetailsResponse
import com.test.bragimovies.data.model.MovieDto
import com.test.bragimovies.domain.model.Genre
import com.test.bragimovies.domain.model.Movie
import com.test.bragimovies.domain.model.MovieDetails

fun GenreDto.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = poster_path,
        rating = vote_average
    )
}

fun MovieDetailsResponse.toDomain(): MovieDetails {
    return MovieDetails(
        id = id,
        budget = budget,
        revenue = revenue
    )
}