package com.example.authrepoapp.model.network

import com.example.authrepoapp.model.SquareModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SquareService {

    private val BASE_URL = "https://api.github.com/"

    private val api: SquareApi

    init {
        api = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SquareApi::class.java)

    }

    suspend fun getSquare(): Response<SquareModel> = api.getSquareApi()


}