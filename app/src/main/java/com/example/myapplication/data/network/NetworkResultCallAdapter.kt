package com.example.myapplication.data.network

import com.example.myapplication.data.Result

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkResultCallAdapter(
    private val resultType: Type
) : CallAdapter<Type, Call<Result<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<Result<Type>> {
        return NetworkResultCall(call)
    }
}