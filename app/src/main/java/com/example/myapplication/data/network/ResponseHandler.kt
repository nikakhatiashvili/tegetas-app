package com.example.myapplication.data.network

import com.example.myapplication.data.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ResponseHandler {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun <T> handleApi(execute: () -> Response<T>): Result<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful) {
                if (body != null) {
                    Result.ApiSuccess(body)
                } else {
                    Result.ApiSuccess("Task added" as T)
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    moshi.adapter(ErrorResponse::class.java).fromJson(it)
                }
                if (errorResponse != null) {
                    Result.ApiError(code = errorResponse.status, message = errorResponse.message)
                } else {
                    Result.ApiError(code = response.code(), message = response.message())
                }
            }
        } catch (e: HttpException) {
            Result.ApiError(code = e.code(), message = e.message())
        } catch (e: IOException) {
            Result.ApiException(e)
        }
    }
}

data class ErrorResponse(val status: Int, val message: String)