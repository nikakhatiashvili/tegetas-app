package com.example.myapplication.data.movie

import com.example.myapplication.domain.MovieRepository
import com.example.myapplication.data.Result
import com.example.myapplication.domain.model.MovieResult
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
): MovieRepository {

    override suspend fun getMovies(): Result<MovieResult> {
        return movieService.getMovies()
    }
}