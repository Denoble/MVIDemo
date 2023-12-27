package com.gevcorst.mvidemo.di

import com.gevcorst.mvidemo.repository.RepoImpl
import com.gevcorst.mvidemo.repository.Repository
import com.gevcorst.mvidemo.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class First

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Second
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Singleton
    @Binds
    @First
    abstract fun providesRepositoryInterfaces(repositoryImpl: RepositoryImpl):Repository

    @Singleton
    @Binds
    @Second
    abstract fun providesRepositoryInterfaces2( repoImpl: RepoImpl):Repository
}