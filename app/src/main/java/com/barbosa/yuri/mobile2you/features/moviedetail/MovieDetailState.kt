package com.barbosa.yuri.mobile2you.features.moviedetail

import com.barbosa.yuri.mobile2you.datasource.models.Movie
import com.barbosa.yuri.mobile2you.datasource.models.SimilarMovies

sealed class MovieDetailState {
    object Initial : MovieDetailState()
    data class Loading(val isLoading: Boolean): MovieDetailState()
    data class Error(val message: String): MovieDetailState()
    data class ShowMovie(val movie: Movie): MovieDetailState()
    data class ShowSimilar(val similarMovies: SimilarMovies): MovieDetailState()
}
