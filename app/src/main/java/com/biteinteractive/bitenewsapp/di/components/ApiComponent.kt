package com.biteinteractive.bitenewsapp.di.components

import com.biteinteractive.bitenewsapp.di.modules.ApiModule
import com.biteinteractive.bitenewsapp.services.ArticleService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: ArticleService)
}