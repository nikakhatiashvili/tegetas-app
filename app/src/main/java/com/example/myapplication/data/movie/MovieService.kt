package com.example.myapplication.data.movie

import com.example.myapplication.common.Constants.GET_MOVIES
import com.example.myapplication.data.Result
import com.example.myapplication.domain.model.MovieResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(GET_MOVIES)
    suspend fun getMovies(@Query("page") page:Int = 1):Result<MovieResult>
}