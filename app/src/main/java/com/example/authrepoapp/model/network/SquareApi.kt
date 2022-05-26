package com.example.authrepoapp.model.network

import com.example.authrepoapp.model.SquareModel
import retrofit2.Response
import retrofit2.http.GET

interface SquareApi {

    @GET("/orgs/square/repos")
    suspend fun getSquareApi(): Response<SquareModel>
}