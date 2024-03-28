package com.example.myapplication.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Result
import com.example.myapplication.domain.MovieRepository
import com.example.myapplication.domain.SearchRepository
import com.example.myapplication.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val delay = 400L

    private val _movies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    val movies = _movies.asStateFlow()
    private val originalMovies = mutableListOf<Movie>()


    private var searchJob: Job? = null

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = movieRepository.getMovies()
            when (data) {
                is Result.ApiSuccess -> {
                    _movies.emit(data.data.results)
                    originalMovies.clear()
                    originalMovies.addAll(data.data.results)
                }
            }
        }
    }

    fun search(text: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(delay)
            if (text.isEmpty()) {
                _movies.emit(originalMovies)
            }else{
                val data = searchRepository.search(text)
                when (data) {
                    is Result.ApiSuccess -> {
                        _movies.emit(data.data.results)
                    }
                }
            }

        }
    }
}