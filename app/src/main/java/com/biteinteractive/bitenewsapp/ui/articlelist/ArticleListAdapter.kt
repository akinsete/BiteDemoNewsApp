package com.biteinteractive.bitenewsapp.ui.articlelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biteinteractive.bitenewsapp.R
import com.biteinteractive.bitenewsapp.data.ArticleItem
import com.biteinteractive.bitenewsapp.util.loadImage
import kotlinx.android.synthetic.main.article_list_item.view.*


class ArticleListAdapter(
    var articles: ArrayList<ArticleItem>,
    var itemClickListener: ((ArticleItem) -> Unit)? = null
) :
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
        val item = articles[position]

        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(item)
        }
    }


    class ArticleListAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val articleThumbnail = view.icon
        private val articleTitle = view.title
        private val articleSection = view.section

        fun bind(article: ArticleItem) {
            article.getThumbnail().let {
                articleThumbnail.loadImage(it.toString())
            }

            articleTitle.text = article.title
            articleSection.text = article.section
        }
    }

}