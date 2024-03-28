package com.example.myapplication.data.detail

import com.example.myapplication.common.Constants
import com.example.myapplication.data.Result
import com.example.myapplication.domain.model.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailService {

    @GET(Constants.GET_DETAILS)
    suspend fun getMovies(@Path("movie_id") movieId:Int): Result<MovieDetail>
}