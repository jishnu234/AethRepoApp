package com.example.authrepoapp.model.network

import com.example.authrepoapp.di.DaggerSquareComponent
import com.example.authrepoapp.model.SquareModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class SquareService {

    @Inject
    lateinit var api: SquareApi

    init {
        /**
         * DaggerSquare Component is a class created by dagger
         * and used to inject the functionalities or data to the
         * variable which is denoted by the annotation @Inject
         */
        DaggerSquareComponent.create().inject(this)
    }

    suspend fun getSquare(): Response<SquareModel> = api.getSquareApi()

}