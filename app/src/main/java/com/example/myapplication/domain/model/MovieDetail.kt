package com.example.myapplication.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    val id:Long,
    @SerializedName("poster_path")
    val poster_path:String? = null,
    @SerializedName("original_title")
    val original_title:String? = null,
    @SerializedName("vote_average")
    val vote_average:Double? = null,
    val overview:String? = null,
    val genres: List<Genres>? = emptyList(),
    val production_companies: List<ProductionCompanies>? = emptyList(),
    val release_date:String? = null
)

