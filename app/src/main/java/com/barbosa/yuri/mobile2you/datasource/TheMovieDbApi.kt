package com.barbosa.yuri.mobile2you.datasource

import com.barbosa.yuri.mobile2you.datasource.models.Movie
import com.barbosa.yuri.mobile2you.datasource.models.SimilarMovies
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbApi {
    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") movieId: Int): Movie

    @GET("movie/{id}/similar")
    suspend fun getSimilar(@Path("id") movieId: Int): SimilarMovies
}