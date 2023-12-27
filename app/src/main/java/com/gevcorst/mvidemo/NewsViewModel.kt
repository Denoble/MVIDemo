package com.gevcorst.mvidemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gevcorst.mvidemo.repository.RepositoryImpl
import com.gevcorst.mvidemo.ui.NewsIntents
import com.gevcorst.mvidemo.ui.NewsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class NewsViewModel() : ViewModel() {
    val channel = Channel<NewsState>()
    private val _newsState = MutableStateFlow<NewsState>(NewsState.loading)
    val newState: StateFlow<NewsState> = _newsState

    init {
        resolveIntents()
    }

    private fun resolveIntents() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect {
                when (it) {
                    NewsIntents.TopHeadLinesIntent -> getMainHeadLines()
                    else -> {}
                }
            }
        }
    }

    private suspend fun getMainHeadLines() {
        RepositoryImpl().getTopHeadLines().collect {
            _newsState.value = it
        }
    }
}