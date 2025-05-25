package com.test.bragimovies.di

import com.test.bragimovies.BuildConfig
import com.test.bragimovies.data.network.MovieApi
import com.test.bragimovies.data.repositiory.MovieRepositoryImpl
import com.test.bragimovies.domain.repositiory.MovieRepository
import com.test.bragimovies.domain.usecase.GetGenresUseCase
import com.test.bragimovies.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = BuildConfig.TMDB_API_KEY

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
            .client(
                OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS).build()
            )
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: MovieApi,
        @ApiKey apiKey: String
    ): MovieRepository {
        return MovieRepositoryImpl(api, apiKey)
    }

    @Provides
    @Singleton
    fun provideGetGenresUseCase(
        repository: MovieRepository
    ): GetGenresUseCase = GetGenresUseCase(repository)

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(
        repository: MovieRepository
    ): GetMoviesUseCase = GetMoviesUseCase(repository)
}
