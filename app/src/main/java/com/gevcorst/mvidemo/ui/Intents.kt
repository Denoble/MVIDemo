package com.gevcorst.mvidemo.ui

import com.gevcorst.mvidemo.model.NewsData

sealed class NewsIntents {
    object topHeadLinesIntent:NewsIntents()
}
sealed class NewsState{
    object loading:NewsState()
    data class Success(val news:NewsData):NewsState()
    data class  Error(val errorMessage:String):NewsState()

}