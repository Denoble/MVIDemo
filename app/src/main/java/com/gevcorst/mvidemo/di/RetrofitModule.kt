package com.gevcorst.mvidemo.di

import com.gevcorst.mvidemo.Constants
import com.gevcorst.mvidemo.repository.RepositoryImpl
import com.gevcorst.mvidemo.repository.network.HeadLineNewsService
import com.gevcorst.mvidemo.repository.network.Interceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule{
    @Provides
    @Singleton
    fun providesInterceptors():Interceptor{
        return Interceptor
    }
    @Singleton
    @Provides
    fun providesOkhttpClient(interceptor: Interceptor):OkHttpClient{
        return    OkHttpClient.Builder()
            .addInterceptor(interceptor) .build()
    }

    @Singleton
    @Provides
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi = Moshi.Builder()
        .add(kotlinJsonAdapterFactory)
        .build()


    @Singleton
    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)
    @Singleton
    @Provides
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory =
        KotlinJsonAdapterFactory()
    @Singleton
    @Provides
    fun providesRetrofit(okHttp: OkHttpClient,
                         moshiConverterFactory: MoshiConverterFactory):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttp)
            .baseUrl(Constants.BASE_URL)
            .build()
    }
    @Singleton
    @Provides
    fun providesNewsAPIService(@Named("TopNews")retrofit: Retrofit):HeadLineNewsService{
       return retrofit.create(HeadLineNewsService::class.java)
    }
    @Singleton
    @Provides
    fun provideRepositoryImp():RepositoryImpl{
        return  RepositoryImpl()
    }
}