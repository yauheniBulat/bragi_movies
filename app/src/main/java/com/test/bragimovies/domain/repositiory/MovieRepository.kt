package com.test.bragimovies.domain.repositiory

import com.test.bragimovies.domain.model.Genre
import com.test.bragimovies.domain.model.Movie
import com.test.bragimovies.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getGenres(): List<Genre>

    fun getMoviesByGenre(genreId: Int?): Flow<List<Movie>>

    suspend fun getMovieDetails(movieId: Int): MovieDetails
}