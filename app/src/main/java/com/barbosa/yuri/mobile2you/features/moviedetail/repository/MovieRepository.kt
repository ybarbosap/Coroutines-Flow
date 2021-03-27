package com.barbosa.yuri.mobile2you.features.moviedetail.repository

import com.barbosa.yuri.mobile2you.datasource.RetrofitClient
import com.barbosa.yuri.mobile2you.datasource.TheMovieDbApi
import com.barbosa.yuri.mobile2you.models.Movie
import com.barbosa.yuri.mobile2you.models.SimilarMovies

// TODO: Criar cache
class MovieRepository {
    fun getMovie(movieId: Int): Movie? {
        val service = RetrofitClient.instance.create(TheMovieDbApi::class.java)
            .getMovie(movieId)
        return service.execute().body()
    }

    fun getSimilarMovies(movieId: Int): SimilarMovies? {
        val service = RetrofitClient.instance.create(TheMovieDbApi::class.java)
            .getSimilar(movieId)
        return  service.execute().body()
    }
}