package com.example.myapplication.ui.main.domain

import com.example.myapplication.ui.main.data.MarsInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=${API_KEY}")
    suspend fun getMarsInfo(@Query(value = "page") page: Int): MarsInfo

    companion object {
        private const val API_KEY = "g1qwnFnQHsSPmcMFffbe4w7zBcYBfhWHJXv01teW"
    }
}