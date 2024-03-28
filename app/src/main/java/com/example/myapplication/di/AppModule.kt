package com.example.myapplication.di

import com.example.myapplication.data.network.NetworkResultCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.example.myapplication.BuildConfig
import com.example.myapplication.common.Constants
import com.example.myapplication.common.Constants.FALSE
import com.example.myapplication.common.Constants.INCLUDE_ADULT
import com.example.myapplication.common.Constants.LANGUAGE_KEY
import com.example.myapplication.data.detail.DetailService
import com.example.myapplication.data.movie.MovieService
import com.example.myapplication.data.search.SearchService
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideBaseRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_WEB_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                )
            ).addCallAdapterFactory(NetworkResultCallAdapterFactory.create()).client(okHttpClient)
            .build()

    val apiKeyInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val modifiedUrl = originalRequest.url.newBuilder()
            .addQueryParameter(INCLUDE_ADULT, FALSE)
            .addQueryParameter(Constants.LANGUAGE, LANGUAGE_KEY)
            .addQueryParameter(Constants.API_KEY, BuildConfig.BASE_API_KEY)
            .build()

        val modifiedRequest = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()

        chain.proceed(modifiedRequest)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Provides
    fun provideDetailService(retrofit: Retrofit): DetailService =
        retrofit.create(DetailService::class.java)

    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)
}