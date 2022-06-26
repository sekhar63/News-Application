package com.example.newsapplication.interfaces

import com.example.newsapplication.Constants.Companion.NEWS_API_KEY
import com.example.newsapplication.Constants.Companion.SOURCE
import com.example.newsapplication.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("top-headlines")
    fun getLatestNews(
        @Query("sources") source: String = SOURCE,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): Call<NewsResponse>
}