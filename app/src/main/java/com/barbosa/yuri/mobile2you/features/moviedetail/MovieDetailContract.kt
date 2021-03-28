package com.barbosa.yuri.mobile2you.features.moviedetail

import com.barbosa.yuri.mobile2you.models.Movie
import com.barbosa.yuri.mobile2you.models.SimilarMovies

/**
 * Contrato do MVP para tela de detalhes dos filmes
 */
interface MovieDetailContract {
    interface View {
        fun displayMovieDetails(similarMovies: SimilarMovies, movie: Movie)
        fun onFailure()
    }

    interface Presenter {
        fun getMovieInfo(movieId: Int)
    }
}