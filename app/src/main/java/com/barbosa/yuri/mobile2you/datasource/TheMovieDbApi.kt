package com.barbosa.yuri.mobile2you.datasource

import com.barbosa.yuri.mobile2you.models.Movie
import com.barbosa.yuri.mobile2you.models.SimilarMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbApi {
    @GET("movie/{id}")
    fun getMovie(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<Movie>

    @GET("movie/{id}/similar")
    fun getSimilar(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<SimilarMovie>
}