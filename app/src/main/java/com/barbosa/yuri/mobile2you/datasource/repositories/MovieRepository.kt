package com.barbosa.yuri.mobile2you.datasource.repositories

import com.barbosa.yuri.mobile2you.datasource.RetrofitClient
import com.barbosa.yuri.mobile2you.datasource.TheMovieDbApi
import com.barbosa.yuri.mobile2you.datasource.models.Movie
import com.barbosa.yuri.mobile2you.datasource.models.SimilarMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository {
    private val service = RetrofitClient.instance.create(TheMovieDbApi::class.java)

    fun getMovie(movieId: Int): Flow<Movie> = flow {
        emit(service.getMovie(movieId))
    }

    fun getSimilarMovies(movieId: Int): Flow<SimilarMovies> = flow {
        emit(service.getSimilar(movieId))
    }
}