package com.biteinteractive.bitenewsapp.ui.articlelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.biteinteractive.bitenewsapp.data.ArticleItem
import com.biteinteractive.bitenewsapp.data.ArticleResponse
import com.biteinteractive.bitenewsapp.data.Resource
import com.biteinteractive.bitenewsapp.di.components.DaggerViewModelComponent
import com.biteinteractive.bitenewsapp.services.ArticleService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleListViewModel : ViewModel() {

    @Inject
    lateinit var articleService: ArticleService
    private val disposable = CompositeDisposable()

    val articles = MutableLiveData<Resource<List<ArticleItem>>>()

    init {
        DaggerViewModelComponent.create().inject(this)
    }

    fun refresh() {
        articles.value = Resource.Loading()
        disposable.add(
            articleService.getHomeArticles().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ArticleResponse>() {
                    override fun onSuccess(articleResponse: ArticleResponse) {
                        articleResponse.results?.let {
                            articles.value = Resource.Success(it)
                        }
                    }

                    override fun onError(e: Throwable) {
                        articles.value = Resource.Failure(e.toString())
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}