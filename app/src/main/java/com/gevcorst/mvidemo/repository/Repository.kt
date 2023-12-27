package com.gevcorst.mvidemo.repository

import android.util.Log
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
            Log.i("Success",response.toString())
            emit(NewsState.Success(response))
        }catch (e:Exception){
            Log.i("Error",e.stackTraceToString())
            emit(NewsState.Error(e.stackTraceToString()))
        }

    }

}