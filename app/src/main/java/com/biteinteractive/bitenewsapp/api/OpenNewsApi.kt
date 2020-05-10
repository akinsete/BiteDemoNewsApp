package com.biteinteractive.bitenewsapp.api

import com.biteinteractive.bitenewsapp.data.ArticleResponse
import io.reactivex.Single
import retrofit2.http.GET

interface OpenNewsApi {
    // AppId = dcb84426-207f-4dc2-b0a4-97cf8a02ef53
    // ApiKey = w4J1Qwty9BzHApcNQOyaKorpjwQam8aq
    // ApiSecret = GWy4RydZBllLVkuM

    @GET("home.json?api-key=dcb84426-207f-4dc2-b0a4-97cf8a02ef53")
    fun getHomeArticles(): Single<ArticleResponse>
//
//    companion object {
//        private const val BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"
//        fun create(): OpenNewsApi = create(HttpUrl.parse(BASE_URL)!!)
//        fun create(httpUrl: HttpUrl): OpenNewsApi {
//            val client = OkHttpClient.Builder()
//                .build()
//            return Retrofit.Builder()
//                .baseUrl(httpUrl)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(OpenNewsApi::class.java)
//        }
//    }
}