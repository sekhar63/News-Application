package com.example.newsapplication.view.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.R
import com.example.newsapplication.databinding.ActivityMainBinding
import com.example.newsapplication.model.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            add<HomeFragment>(R.id.fragment_container_view)
//        }
//        mBinding.newsRv.layoutManager = LinearLayoutManager(this)
//        observerNews()
//        mainViewModel.getLatestNews()
    }

//    private fun observerNews() {
//        mainViewModel.newsResponse.observe(this, {
//            it?.let {
//                val onItemClick: (article: Article) -> Unit = {
//                    Log.d("sekhar", "article recived")
//                    mainViewModel.selectedArticle.value = it
//                }
//                mBinding.newsRv.adapter = NewsAdapter(onItemClick, it.articles)
//            }
//        })
//
//        mainViewModel.isLoading.observe(this, {
//            if (it) {
//                mBinding.progressbar.visibility = View.VISIBLE
//                mBinding.progressbar.show()
//                mBinding.newsRv.visibility = View.GONE
//            } else {
//                mBinding.progressbar.visibility = View.GONE
//                mBinding.progressbar.hide()
//                mBinding.newsRv.visibility = View.VISIBLE
//            }
//        })
//    }
}