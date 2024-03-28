package com.example.myapplication.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Result
import com.example.myapplication.domain.DetailRepository
import com.example.myapplication.domain.MovieRepository
import com.example.myapplication.domain.model.MovieDetail
import com.example.myapplication.domain.model.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {

    private val _movie: MutableStateFlow<MovieDetail?> = MutableStateFlow(null)
    val movie = _movie.asStateFlow()

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun getDetails(movieId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            _loading.emit(true)
            val data = detailRepository.getDetails(movieId)
            when(data){
                is Result.ApiSuccess -> {
                    _movie.emit(data.data)
                    _loading.emit(false)
                }
                else -> {
                    _loading.emit(false)
                }
            }
        }
    }
}