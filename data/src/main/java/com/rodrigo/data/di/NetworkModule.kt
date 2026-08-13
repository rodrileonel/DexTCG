package com.rodrigo.data.di

import com.rodrigo.data.api.Api
import com.rodrigo.data.repository.RepositoryImpl
import com.rodrigo.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit = Retrofit.Builder()
        .baseUrl("https://api.pokemontcg.io/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Provides
    fun provideRepository(api: Api): Repository = RepositoryImpl(api)
}