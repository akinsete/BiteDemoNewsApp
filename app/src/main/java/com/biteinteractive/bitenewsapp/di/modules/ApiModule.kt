package com.biteinteractive.bitenewsapp.di.modules

import com.biteinteractive.bitenewsapp.api.OpenNewsApi
import com.biteinteractive.bitenewsapp.services.ArticleService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"

    @Provides
    fun provideOpenNewsApi(): OpenNewsApi {
        return Retrofit.Builder()
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