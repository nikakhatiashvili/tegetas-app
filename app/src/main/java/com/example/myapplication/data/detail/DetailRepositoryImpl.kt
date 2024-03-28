package com.example.myapplication.data.detail

import com.example.myapplication.data.Result
import com.example.myapplication.domain.DetailRepository
import com.example.myapplication.domain.model.MovieDetail
import com.example.myapplication.domain.model.MovieResult
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailService:DetailService
) :DetailRepository {

    override suspend fun getDetails(movieId: Int): Result<MovieDetail> {
        return detailService.getMovies(movieId = movieId)
    }
}