package com.example.newsapplication.view.main

import com.example.newsapplication.interfaces.RetrofitService
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val retrofitService: RetrofitService,
    val scope: CoroutineScope
) {
    fun getLatestNews() =
        retrofitService.getLatestNews()
}