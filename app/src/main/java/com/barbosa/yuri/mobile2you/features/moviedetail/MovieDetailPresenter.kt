package com.barbosa.yuri.mobile2you.features.moviedetail

import com.barbosa.yuri.mobile2you.datasource.repositories.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailPresenter(
    private val movieRepository: MovieRepository,
    private val view: MovieDetailContract.View,
) : MovieDetailContract.Presenter {

    /**
     * Busca detelhes e filmes similares do filme
     */
    override fun getMovieInfo(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = movieRepository.getMovie(movieId)
            val similar = movieRepository.getSimilarMovies(movieId)
            withContext(Dispatchers.Main) {
                if (movie != null && similar != null) {
                    view.displayMovieDetails(similar, movie)
                } else {
                    view.onFailure()
                }
            }
        }
    }
}