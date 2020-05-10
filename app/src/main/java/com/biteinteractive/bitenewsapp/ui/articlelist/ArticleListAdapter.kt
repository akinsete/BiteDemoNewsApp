package com.biteinteractive.bitenewsapp.ui.articlelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biteinteractive.bitenewsapp.R
import com.biteinteractive.bitenewsapp.data.ArticleItem


class ArticleListAdapter(var articles: ArrayList<ArticleItem>) :
    RecyclerView.Adapter<ArticleListAdapter.ArticleListAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ArticleListAdapterViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.article_list_item, parent, false
        )
    )

    fun updateArticles(updatedArticles: List<ArticleItem>) {
        articles.clear()
        articles.addAll(updatedArticles)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleListAdapterViewHolder, position: Int) {
        holder.bind(articles[position])
    }


    class ArticleListAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //        private val countryName = view.name
//        private val imageView = view.imageView
//        private val countryCapital = view.capital
//        private val progressDrawable  = getProgressDrawable(view.context)
//
        fun bind(article: ArticleItem) {
//            countryName.text = country.countryName
//            countryCapital.text = country.capital
//            imageView.loadImage(country.flag,progressDrawable)
        }
    }

}