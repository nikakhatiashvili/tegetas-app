package com.example.myapplication.data.search

import com.example.myapplication.data.Result
import com.example.myapplication.domain.SearchRepository
import com.example.myapplication.domain.model.MovieResult
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService:SearchService
):SearchRepository {

    override suspend fun search(text: String): Result<MovieResult> {
        return searchService.getMovies(text)
    }
}