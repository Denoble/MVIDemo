package com.gevcorst.mvidemo.repository

import com.gevcorst.mvidemo.repository.network.HeadLinesNewsServiceObject
import com.gevcorst.mvidemo.ui.NewsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface Repository{
    suspend fun getTopHeadLines(): Flow<NewsState>
}

class RepositoryImpl():Repository{
    override suspend fun getTopHeadLines(): Flow<NewsState>  = flow{
        emit(NewsState.loading)
        try {
            val differedResponse = HeadLinesNewsServiceObject.headLineNewsService.getHeadLins()
          val response =  differedResponse.await()
            emit(NewsState.Success(response))
        }catch (e:Exception){
            emit(NewsState.Error(e.stackTraceToString()))
        }

    }

}