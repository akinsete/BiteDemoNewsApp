package com.biteinteractive.bitenewsapp.data

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("status") val status: String? = null,
    @SerializedName("num_results") val num_results: Int? = 0,
    @SerializedName("results") val results: List<ArticleItem>? = null
)