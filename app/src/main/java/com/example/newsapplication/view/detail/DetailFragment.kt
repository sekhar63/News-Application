package com.example.newsapplication.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.newsapplication.Utils
import com.example.newsapplication.databinding.DetailFragmentBinding
import com.example.newsapplication.view.main.MainViewModel

class DetailFragment : Fragment() {
    lateinit var mBinding: DetailFragmentBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DetailFragmentBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setValues()
    }

    private fun setValues() {
        mainViewModel.getSelectedArticle().let {
            mBinding.title.text = it.title
            mBinding.newsDescriptionTv.text = it.description
            Glide.with(mContext).load(it.urlToImage).into(mBinding.imageView)
            mBinding.publishDateTv.text = Utils.convertTime(it.publishedAt)
            val url = it.url
            mBinding.shareIv.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, url)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            mBinding.backIv.setOnClickListener {
                findNavController().popBackStack()
            }


        }
    }
}