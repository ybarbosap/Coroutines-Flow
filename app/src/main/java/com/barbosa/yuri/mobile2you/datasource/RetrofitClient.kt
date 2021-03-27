package com.barbosa.yuri.mobile2you.datasource

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitClient {
    companion object {
        val instance: Retrofit = Retrofit.Builder()
            .baseUrl(TheMovieDbApiHelper.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()

                    val url = original.url().newBuilder()
                        .addQueryParameter("api_key", TheMovieDbApiHelper.API_KEY)
                        .build()

                    val requestBuilder = original.newBuilder().url(url)
                    val request = requestBuilder.build()

                    chain.proceed(request)
                }.build())
            .build()
    }
}