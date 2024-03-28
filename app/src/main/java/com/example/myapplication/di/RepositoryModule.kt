package com.example.myapplication.di

import com.example.myapplication.data.detail.DetailRepositoryImpl
import com.example.myapplication.data.movie.MovieRepositoryImpl
import com.example.myapplication.data.search.SearchRepositoryImpl
import com.example.myapplication.domain.DetailRepository
import com.example.myapplication.domain.MovieRepository
import com.example.myapplication.domain.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun bindsMovieDetailRepository(
        detailRepositoryImpl: DetailRepositoryImpl
    ): DetailRepository

    @Binds
    @Singleton
    abstract fun bindsSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository
}