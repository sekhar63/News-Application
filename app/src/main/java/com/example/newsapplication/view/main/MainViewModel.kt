package com.example.newsapplication.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapplication.CountingIdlingResourceSingleton
import com.example.newsapplication.model.Article
import com.example.newsapplication.model.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val scope: CoroutineScope
) : ViewModel() {
    val newsResponse = MutableLiveData<NewsResponse>()
    val isLoading = MutableLiveData<Boolean>()
    val isErrorMsg = MutableLiveData<String>()
    private val _selectedArticle = MutableLiveData<Article>()

    fun setSelectedArticle(item: Article) {
        _selectedArticle.value = item
    }

    fun getSelectedArticle(): Article {
        return _selectedArticle.value!!
    }

    fun getLatestNews() {
        scope.launch {
            CountingIdlingResourceSingleton.increment()
            isLoading.postValue(true)
            val response = mainRepository.getLatestNews()
            response.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    isLoading.postValue(false)
                    newsResponse.postValue(response.body())
                    CountingIdlingResourceSingleton.decrement()
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    isLoading.postValue(false)
                    isErrorMsg.postValue(t.message)
                    CountingIdlingResourceSingleton.decrement()
                }

            })
        }

    }
}