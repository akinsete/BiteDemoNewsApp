package com.biteinteractive.bitenewsapp.di.components

import com.biteinteractive.bitenewsapp.di.modules.ApiModule
import com.biteinteractive.bitenewsapp.ui.articlelist.ArticleListViewModel
import dagger.Component


@Component(modules = [ApiModule::class])
interface ViewModelComponent {
    fun inject(viewModel: ArticleListViewModel)
}