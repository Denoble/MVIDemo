package com.gevcorst.mvidemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gevcorst.mvidemo.databinding.NewsItemBinding
import com.gevcorst.mvidemo.model.Article
import com.gevcorst.mvidemo.model.NewsData

 class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var articles = listOf<Article>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       val itemBinding = NewsItemBinding.inflate(LayoutInflater.from(
           parent.context),parent,false)
        return NewsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
     return articles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }
    fun addArticleItem(articles: List<Article>){
        this.articles =  articles
        notifyDataSetChanged()
    }
    class NewsViewHolder(val itemBinding: NewsItemBinding) : RecyclerView.ViewHolder(
        itemBinding.root
    ) {
        fun bind(article: Article) {
            itemBinding.title.text = article.title
            itemBinding.description.text = article.description
        }

    }
}