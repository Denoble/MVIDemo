package com.gevcorst.mvidemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gevcorst.mvidemo.di.First
import com.gevcorst.mvidemo.repository.Repository
import com.gevcorst.mvidemo.repository.RepositoryImpl
import com.gevcorst.mvidemo.ui.NewsIntents
import com.gevcorst.mvidemo.ui.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(@First val repo:
                                        Repository) : ViewModel() {
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
                    NewsState.loading ->{}
                    is NewsState.Error ->{

                    }
                    else -> {}
                }
            }
        }
    }

    private suspend fun getMainHeadLines() {
        repo.getTopHeadLines().collect {
            _newsState.value = it
        }
    }
}