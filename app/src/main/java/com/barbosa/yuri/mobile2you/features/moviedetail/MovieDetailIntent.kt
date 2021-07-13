package com.barbosa.yuri.mobile2you.features.moviedetail

sealed class MovieDetailIntent {
    object GetMovie: MovieDetailIntent()
    object GetSimilar: MovieDetailIntent()
    data class InitArgs(val movieID: Int): MovieDetailIntent()
}
