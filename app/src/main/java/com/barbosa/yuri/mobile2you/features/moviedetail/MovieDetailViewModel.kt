package com.barbosa.yuri.mobile2you.features.moviedetail

import com.barbosa.yuri.mobile2you.models.Movie
import com.barbosa.yuri.mobile2you.models.SimilarMovies

data class MovieDetailViewModel(
    val similarMovies: SimilarMovies,
    val movie: Movie,
)
