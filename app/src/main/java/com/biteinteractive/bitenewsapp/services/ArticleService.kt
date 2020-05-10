package com.biteinteractive.bitenewsapp.services

import com.biteinteractive.bitenewsapp.api.OpenNewsApi
import com.biteinteractive.bitenewsapp.data.ArticleResponse
import com.biteinteractive.bitenewsapp.di.components.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class ArticleService {

    @Inject
    lateinit var api: OpenNewsApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getHomeArticles(): Single<ArticleResponse> {
        return api.getHomeArticles()
    }
}