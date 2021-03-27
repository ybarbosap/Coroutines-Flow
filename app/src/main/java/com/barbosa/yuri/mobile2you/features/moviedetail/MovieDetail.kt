package com.barbosa.yuri.mobile2you.features.moviedetail

/**
 * Contrato do MVP para tela de detalhes dos filmes
 */
interface MovieDetailContract {
    interface View {
        fun displayMovieDetails(movieDetailViewModel: MovieDetailViewModel)
        fun onFailure()
    }

    interface Presenter {
        fun getMovieInfo(movieId: Int)
    }
}