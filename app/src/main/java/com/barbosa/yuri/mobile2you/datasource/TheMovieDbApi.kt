package com.barbosa.yuri.mobile2you.datasource

import com.barbosa.yuri.mobile2you.models.Movie
import com.barbosa.yuri.mobile2you.models.SimilarMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbApi {
    @GET("movie/{id}")
    fun getMovie(
        @Path("id") movieId: Int,
    ): Call<Movie>

    @GET("movie/{id}/similar")
    fun getSimilar(
        @Path("id") movieId: Int,
    ): Call<SimilarMovies>
}