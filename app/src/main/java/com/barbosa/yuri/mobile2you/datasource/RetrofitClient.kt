package com.barbosa.yuri.mobile2you.datasource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        fun instance(): Retrofit = Retrofit.Builder()
            .baseUrl(TheMovieDbApiHelper.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}