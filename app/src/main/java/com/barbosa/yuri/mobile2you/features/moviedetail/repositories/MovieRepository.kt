package com.barbosa.yuri.mobile2you.features.moviedetail.repositories

import com.barbosa.yuri.mobile2you.datasource.RetrofitClient
import com.barbosa.yuri.mobile2you.datasource.TheMovieDbApi
import com.barbosa.yuri.mobile2you.datasource.TheMovieDbApiHelper
import com.barbosa.yuri.mobile2you.models.Movie

class MovieRepository {
    fun getMovie(movieId: Int): Movie? {
        val service = RetrofitClient.instance().create(TheMovieDbApi::class.java)
            .getMovie(movieId, TheMovieDbApiHelper.API_KEY)
        return service.execute().body()
    }
}