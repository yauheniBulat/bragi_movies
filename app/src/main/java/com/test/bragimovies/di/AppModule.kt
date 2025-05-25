package com.test.bragimovies.di

import com.test.bragimovies.data.network.MovieApi
import com.test.bragimovies.data.repositiory.MovieRepositoryImpl
import com.test.bragimovies.domain.repositiory.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "YOUR_API_KEY_HERE"

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(): String = API_KEY

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: MovieApi,
        apiKey: String
    ): MovieRepository {
        return MovieRepositoryImpl(api, apiKey)
    }
}
