package com.gevcorst.mvidemo.di

import com.gevcorst.mvidemo.repository.Repository
import com.gevcorst.mvidemo.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Singleton
    @Binds
    abstract fun providesRepositoryInterfaces(repositoryImpl: RepositoryImpl):Repository
}