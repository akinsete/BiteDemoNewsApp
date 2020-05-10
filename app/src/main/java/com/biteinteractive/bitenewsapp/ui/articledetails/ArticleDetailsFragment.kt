package com.biteinteractive.bitenewsapp.ui.articledetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.biteinteractive.bitenewsapp.R
import com.biteinteractive.bitenewsapp.ui.MainActivityDelegate
import com.biteinteractive.bitenewsapp.util.loadImage
import kotlinx.android.synthetic.main.fragment_article_detail.*

/**
 * A simple [Fragment] subclass.
 */
class ArticleDetailsFragment : Fragment() {

    private lateinit var mainActivityDelegate: MainActivityDelegate
    private val mainActivityArgs by navArgs<ArticleDetailsFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mainActivityDelegate = context as MainActivityDelegate
        } catch (e: ClassCastException) {
            throw ClassCastException()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayArticleItem()
    }

    private fun displayArticleItem() {
        val article = mainActivityArgs.articleItem
        article.getThumbnail().let {
            articleBanner.loadImage(it.toString())
        }
        articleTitle.text = article.title
        articleAbstract.text = article.abstract


        mainActivityDelegate.setupToolbar(
            articleDetailToolBar,
            titleResId = null,
            titleString = article.title,
            backEnabled = true
        )
    }
}
