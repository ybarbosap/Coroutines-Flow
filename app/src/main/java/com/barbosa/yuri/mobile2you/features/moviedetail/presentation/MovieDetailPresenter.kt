package com.barbosa.yuri.mobile2you.features.moviedetail.presentation

import com.barbosa.yuri.mobile2you.features.moviedetail.repositories.MovieRepository
import com.barbosa.yuri.mobile2you.features.moviedetail.repositories.SimilarRepository
import com.barbosa.yuri.mobile2you.models.Movie
import com.barbosa.yuri.mobile2you.models.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailPresenter(
    private val similarRepository: SimilarRepository,
    private val movieRepository: MovieRepository,
    private val listener: MoviePresenterContract
) {

    fun getMovie(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch{
            val movie: Movie? = movieRepository.getMovie(movieId)
            val similar = similarRepository.getSimilar(movieId)
            withContext(Dispatchers.Main){
                if (movie != null && similar != null) {
                    val response = Response(similar!!, movie!!)
                    listener.onSuccess(response)
                }else{
                    listener.onFailure()
                }
                listener.onComplete()
            }
        }
    }

    companion object {
        interface MoviePresenterContract {
            fun onSuccess(response: Response)
            fun onFailure()
            fun onComplete()
        }
    }
}