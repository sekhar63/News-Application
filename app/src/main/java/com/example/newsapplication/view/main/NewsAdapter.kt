package com.example.newsapplication.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.Utils
import com.example.newsapplication.databinding.NewsItemBinding
import com.example.newsapplication.model.Article

class NewsAdapter(
    var onItemClick: (article: Article) -> Unit,
    private var newsArticles: List<Article>
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(var newsItemBinding: NewsItemBinding) :
        RecyclerView.ViewHolder(newsItemBinding.root) {

        fun bind(item: Article, onItemClick: (article: Article) -> Unit) {
            newsItemBinding.apply {
                this.newsTitleTv.text = item.title
                this.newsDescriptionTv.text = item.content
                this.cardView.setOnClickListener {
                    onItemClick.invoke(item)
                }
                Glide.with(this.imageView.context).load(item.urlToImage)
                    .placeholder(R.drawable.ic_default_image)
                    .centerCrop().into(this.imageView)
                this.publishedTv.text = Utils.convertTime(item.publishedAt)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsArticles[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return newsArticles.size
    }

    fun clear() {
        newsArticles.toMutableList().clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(list: List<Article>) {
        newsArticles.toMutableList().addAll(list)
        notifyDataSetChanged()
    }

}
