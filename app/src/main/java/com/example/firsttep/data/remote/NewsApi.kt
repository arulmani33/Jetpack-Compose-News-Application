package com.example.firsttep.data.remote

import com.example.firsttep.data.remote.dto.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(@Query("apiKey") apiKey: String, @Query("country") country: String) : News


    @GET("/v2/top-headlines")
    suspend fun getEveryNews(@Query("apiKey") apiKey: String, @Query("q") query: String) : News

}