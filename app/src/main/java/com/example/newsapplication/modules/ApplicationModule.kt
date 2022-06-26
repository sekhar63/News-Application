package com.example.newsapplication.modules

import com.example.newsapplication.Constants.Companion.BASE_URL
import com.example.newsapplication.interfaces.RetrofitService
import com.example.newsapplication.view.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    val TAG = "newsApplication"
    // lateinit var retrofitService: RetrofitService

    //  lateinit var coroutineScope: CoroutineScope

    @Provides
    @Singleton
    fun provideRetrofit(): RetrofitService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RetrofitService::class.java)
    }

    @Provides
    fun provideCoroutineScope(): CoroutineScope {
        val parentJob = Job()
        val coroutineContext = parentJob + Dispatchers.IO
        return CoroutineScope(coroutineContext)
    }

    @Provides
    fun provideMainRepository(
        retrofitService: RetrofitService,
        scope: CoroutineScope
    ): MainRepository {
        return MainRepository(retrofitService, scope)
    }
}