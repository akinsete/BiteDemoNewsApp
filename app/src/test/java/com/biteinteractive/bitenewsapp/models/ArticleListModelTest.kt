package com.biteinteractive.bitenewsapp.models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.biteinteractive.bitenewsapp.data.ArticleItem
import com.biteinteractive.bitenewsapp.data.ArticleResponse
import com.biteinteractive.bitenewsapp.data.Resource
import com.biteinteractive.bitenewsapp.services.ArticleService
import com.biteinteractive.bitenewsapp.ui.articlelist.ArticleListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


class ArticleListModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var articleService: ArticleService

    @InjectMocks
    var articleListViewModel = ArticleListViewModel()

    private var testSingle: Single<ArticleResponse>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getArticleResponseSuccess() {
        val articleItem = ArticleItem(title = "Sample Title")
        val articleList = arrayListOf(articleItem)

        val articleResponse = ArticleResponse(results = articleList)

        testSingle = Single.just(articleResponse)

        Mockito.`when`(articleService.getHomeArticles()).thenReturn(testSingle)

        articleListViewModel.refresh()

        Assert.assertThat(
            articleListViewModel.articles.value,
            instanceOf(Resource.Success::class.java)
        )

        Assert.assertEquals(1, articleListViewModel.articles.value?.data?.size)
    }

    @Test
    fun getArticleResponseFail() {

        testSingle = Single.error(Throwable())

        Mockito.`when`(articleService.getHomeArticles()).thenReturn(testSingle)

        articleListViewModel.refresh()

        Assert.assertThat(
            articleListViewModel.articles.value,
            instanceOf(Resource.Failure::class.java)
        )

        Assert.assertEquals(null, articleListViewModel.articles.value?.data)
    }


    @Before
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, false)
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }

}