package com.gevcorst.mvidemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.gevcorst.mvidemo.NewsViewModel
import com.gevcorst.mvidemo.R
import com.gevcorst.mvidemo.databinding.ActivityNewsBinding
import com.gevcorst.mvidemo.databinding.NewsItemBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class NewsActivity : AppCompatActivity() {
    val viewModel: NewsViewModel by viewModels()
    val adapter = NewsAdapter()
    lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpRecyclerView(binding)
    }
    fun setUpRecyclerView(binding: ActivityNewsBinding) {
        binding.rvNews.apply {
            this.layoutManager = GridLayoutManager(context,1).apply {
            }
            this.adapter = adapter
        }
        binding.rvNews.adapter = adapter
        lifecycleScope.launch {
            viewModel.channel.send(NewsIntents.TopHeadLinesIntent)
        }
        lifecycleScope.launch {
            viewModel.newState.collect{
                when(it){
                    is NewsState.Success -> {
                        adapter.addArticleItem(it.news.articles)
                    }
                    is NewsState.Error ->{

                    }
                    is NewsState.loading ->{

                    }
                    else ->{

                    }
                }
            }
        }

    }
}