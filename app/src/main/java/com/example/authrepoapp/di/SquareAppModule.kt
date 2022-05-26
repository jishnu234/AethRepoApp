package com.example.authrepoapp.di

import com.example.authrepoapp.model.network.SquareApi
import com.example.authrepoapp.model.network.SquareService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class SquareAppModule {

    private val BASE_URL = "https://api.github.com/"

    @Provides
    fun provideSquareApi(): SquareApi =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SquareApi::class.java)

    @Provides
    fun provideSquareService(): SquareService = SquareService()
}