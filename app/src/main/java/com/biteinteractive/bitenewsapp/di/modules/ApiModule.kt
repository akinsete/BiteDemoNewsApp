package com.biteinteractive.bitenewsapp.di.modules

import com.biteinteractive.bitenewsapp.api.OpenNewsApi
import com.biteinteractive.bitenewsapp.services.ArticleService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class ApiModule {

    private val BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"

    @Provides
    fun provideOpenNewsApi(): OpenNewsApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(OpenNewsApi::class.java)
    }

    @Provides
    fun provideCountriesService(): ArticleService {
        return ArticleService()
    }

}