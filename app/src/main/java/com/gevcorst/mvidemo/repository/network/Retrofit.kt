package com.gevcorst.mvidemo.repository.network

import com.gevcorst.mvidemo.BuildConfig
import com.gevcorst.mvidemo.Constants
import com.gevcorst.mvidemo.model.NewsData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Constants.BASE_URL)
    .client(okhttpClient())
    .build()

interface HeadLineNewsService {
    @GET("search?q=example&lang=en&country=us&max=10&apikey=${BuildConfig.NEWS_API_KEY}")
    fun getHeadLins(): Deferred<NewsData>
}

object Interceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        return chain.proceed(request)
    }
}

/**
 * Initialize OkhttpClient with our Interceptor
 */
private fun okhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(com.gevcorst.mvidemo.repository.network.Interceptor)
        .build()

}

object HeadLinesNewsServiceObject {
    val headLineNewsService: HeadLineNewsService
            by lazy {
                retrofit.create(HeadLineNewsService::class.java)
            }
}
