package com.biteinteractive.bitenewsapp.api

import com.biteinteractive.bitenewsapp.data.ArticleResponse
import io.reactivex.Single
import retrofit2.http.GET

interface OpenNewsApi {
    // ApiKey = w4J1Qwty9BzHApcNQOyaKorpjwQam8aq
    @GET("home.json?api-key=w4J1Qwty9BzHApcNQOyaKorpjwQam8aq")
    fun getHomeArticles(): Single<ArticleResponse>

}