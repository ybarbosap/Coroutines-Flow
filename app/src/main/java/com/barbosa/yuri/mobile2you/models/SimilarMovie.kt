package com.barbosa.yuri.mobile2you.models


import com.google.gson.annotations.SerializedName

data class SimilarMovie(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)