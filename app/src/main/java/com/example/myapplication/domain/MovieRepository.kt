package com.example.myapplication.domain

import com.example.myapplication.data.Result
import com.example.myapplication.domain.model.MovieResult

interface MovieRepository {
    suspend fun getMovies():Result<MovieResult>
}