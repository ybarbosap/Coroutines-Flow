package com.barbosa.yuri.mobile2you.features.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.barbosa.yuri.mobile2you.datasource.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val state = MutableStateFlow<MovieDetailState>(MovieDetailState.Initial)
    private var movieID: Int? = null

    fun handle(intent: MovieDetailIntent) {
        when (intent) {
            is MovieDetailIntent.GetMovie -> getMovieInfo()
            is MovieDetailIntent.GetSimilar -> getSimilar()
            is MovieDetailIntent.InitArgs -> initArgs(intent.movieID)
        }
    }

    private fun initArgs(movieId: Int) {
        this.movieID = movieId
    }

    private fun getMovieInfo() {
        movieID?.let {
            viewModelScope.launch {
                state.value = MovieDetailState.Loading(true)
                movieRepository
                    .getMovie(it)
                    .flowOn(Dispatchers.IO)
                    .catch { error ->
                        state.value = MovieDetailState.Error(error.message.orEmpty())
                    }.onCompletion {
                        state.value = MovieDetailState.Loading(false)
                    }.collect {
                        state.value = MovieDetailState.ShowMovie(it)
                    }
            }
        }
    }

    private fun getSimilar() {
        movieID?.let {
            viewModelScope.launch {
                state.value = MovieDetailState.Loading(true)
                movieRepository
                    .getSimilarMovies(it)
                    .flowOn(Dispatchers.IO)
                    .catch { error ->
                        state.value = MovieDetailState.Error(error.message.orEmpty())
                    }.onCompletion {
                        state.value = MovieDetailState.Loading(false)
                    }.collect {
                        state.value = MovieDetailState.ShowSimilar(it)
                    }
            }
        }
    }

    class Factory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailViewModel(movieRepository) as T
        }
    }
}