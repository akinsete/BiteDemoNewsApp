package com.biteinteractive.bitenewsapp.ui.articlelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.biteinteractive.bitenewsapp.R
import com.biteinteractive.bitenewsapp.data.Resource
import com.biteinteractive.bitenewsapp.ui.MainActivityDelegate
import kotlinx.android.synthetic.main.fragment_article_list.*


/**
 * A simple [Fragment] subclass.
 */
class ArticleListFragment : Fragment() {

    private lateinit var viewModel: ArticleListViewModel

    private lateinit var mainActivityDelegate: MainActivityDelegate

    private var articlesAdapter: ArticleListAdapter = ArticleListAdapter(arrayListOf())

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
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivityDelegate.setupToolbar(
            toolbar = articleToolBar,
            titleResId = R.string.articles,
            titleString = null,
            backEnabled = false
        )

        initiateRecyclerView()

        initiateViewModel()
    }

    private fun initiateRecyclerView() {
        articleRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articlesAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        articlesAdapter.itemClickListener = {
            findNavController().navigate(
                ArticleListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(
                    it
                )
            )
        }
    }

    private fun initiateViewModel() {
        viewModel = ViewModelProviders.of(this).get(ArticleListViewModel::class.java)

        observeViewModel()

        viewModel.refresh()
    }

    private fun observeViewModel() {
        viewModel.articles.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    progressBar.visibility = VISIBLE
                }
                is Resource.Failure -> {
                    progressBar.visibility = GONE
                    loadingError.visibility = VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = GONE
                    loadingError.visibility = GONE
                    resource.data?.let {
                        articlesAdapter.updateArticles(it)
                    }
                }
            }
        })
    }

}
