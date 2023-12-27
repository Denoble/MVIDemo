package com.gevcorst.mvidemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.gevcorst.mvidemo.NewsViewModel
import com.gevcorst.mvidemo.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by viewModels()
    private val adapter = NewsAdapter()
    private lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpRecyclerView(binding)
    }

    fun setUpRecyclerView(binding: ActivityNewsBinding) {
        binding.rvNews.apply {
            this.layoutManager = GridLayoutManager(context, 1).apply {
            }
            this.adapter = adapter
        }
        binding.rvNews.adapter = adapter
        lifecycleScope.launch {
         viewModel.channel.send(NewsIntents.TopHeadLinesIntent)
        }
        lifecycleScope.launch {
            viewModel.newState.collect {
                when (it) {
                    is NewsState.Success -> {
                        adapter.addArticleItem(it.news.articles)
                    }

                    is NewsState.Error -> {

                    }

                    is NewsState.loading -> {

                    }

                    else -> {

                    }
                }
            }
        }

    }
}