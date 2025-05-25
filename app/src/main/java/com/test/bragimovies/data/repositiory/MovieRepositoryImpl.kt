package com.test.bragimovies.data.repositiory

import com.test.bragimovies.data.mapper.toDomain
import com.test.bragimovies.data.network.MovieApi
import com.test.bragimovies.di.ApiKey
import com.test.bragimovies.domain.model.Genre
import com.test.bragimovies.domain.model.Movie
import com.test.bragimovies.domain.model.MovieDetails
import com.test.bragimovies.domain.repositiory.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    @ApiKey private val apiKey: String
) : MovieRepository {
    override suspend fun getGenres(): List<Genre> {
        return api.getGenres(apiKey).genres.map { it.toDomain() }
    }

    override fun getMoviesByGenre(genreId: Int?): Flow<List<Movie>> = flow {
            repeat(3) { attempt ->
                try {
                    val movies = api.getMoviesByGenre(apiKey, genreId).results.map { it.toDomain() }
                    emit(movies)
                    return@flow
                } catch (e: Exception) {
                    if (attempt == 2) throw e
                    delay(200)
                }
            }
        }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return api.getMovieDetails(movieId, apiKey).toDomain()
    }
}