package com.example.myunsplashphotosapp.module

import com.example.myunsplashphotosapp.Api.api_service
import com.example.myunsplashphotosapp.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun retrofitinstance() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Utils.Base_Url_Of_Unsplah)
        .build()
        .create(api_service::class.java)
}