package com.lahza.todo.di.module

import com.lahza.todo.retrofit.CountryApi
import com.lahza.todo.retrofit.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideApiInterface(): CountryApi = RetrofitInstance.api
}
