package com.example.myapplication.data.search

import com.example.myapplication.common.Constants
import com.example.myapplication.data.Result
import com.example.myapplication.domain.model.MovieResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET(Constants.SEARCH)
    suspend fun getMovies(@Query("query") text:String): Result<MovieResult>
}