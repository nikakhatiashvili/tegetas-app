package com.example.myapplication.data

sealed class Result<T> {

    class ApiSuccess<T>(val data: T) : Result<T>()

    class ApiError<T>(val code: Int?, val message: String?) : Result<T>()

    class ApiException<T>(val e: Throwable) : Result<T>()
}