package com.example.myapplication.domain

import com.example.myapplication.data.Result
import com.example.myapplication.domain.model.MovieDetail
import com.example.myapplication.domain.model.MovieResult

interface DetailRepository {
    suspend fun getDetails(movieId:Int): Result<MovieDetail>
}