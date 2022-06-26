package com.example.newsapplication.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.newsapplication.R
import com.example.newsapplication.databinding.HomeFragmentBinding
import com.example.newsapplication.model.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var mBinding: HomeFragmentBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = HomeFragmentBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerNews()
        mainViewModel.getLatestNews()
    }


    private fun observerNews() {
        mainViewModel.newsResponse.observe(this, {
            it?.let {
                val onItemClick: (article: Article) -> Unit = {
                    mainViewModel.setSelectedArticle(it)
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                }
                mBinding.newsRv.adapter = NewsAdapter(onItemClick, it.articles)

                mBinding.newsRv.visibility = View.VISIBLE
            }
        })

        mainViewModel.isLoading.observe(this, {
            if (it) {

                mBinding.newsRv.visibility = View.GONE
                mBinding.errorTv.visibility = View.GONE
                mBinding.retryBtn.visibility = View.GONE
                mBinding.progressbar.visibility = View.VISIBLE
                mBinding.progressbar.show()

            } else {
                mBinding.progressbar.visibility = View.GONE
                mBinding.progressbar.hide()
            }
        })
        mainViewModel.isErrorMsg.observe(this, { msg ->
            mBinding.errorTv.visibility = View.VISIBLE
            mBinding.retryBtn.visibility = View.VISIBLE
            mBinding.errorTv.text = msg
        })
        mBinding.retryBtn.setOnClickListener {
            mainViewModel.getLatestNews()
        }
    }
}