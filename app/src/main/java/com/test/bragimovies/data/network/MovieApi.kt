package com.test.bragimovies.data.network

import com.test.bragimovies.data.model.GenreResponse
import com.test.bragimovies.data.model.MovieDetailsResponse
import com.test.bragimovies.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): GenreResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: Int?
    ): MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailsResponse
}