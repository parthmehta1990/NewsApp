package com.example.weatherapp.api

import com.example.weatherapp.BuildConfig.API_KEY
import com.example.weatherapp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    //Used to define the request to get the data

    //Here we define the type of API

    // This is the network call function so we have to make API Call
    // async so we have to use the coroutine for that and use suspend functions
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode:String="us",

        @Query("page")
        pageNumber:Int=1,

        @Query("apiKey")
        apiKey:String=API_KEY
    ):Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery:String,

        @Query("page")
        pageNumber:Int=1,

        @Query("apiKey")
        apiKey:String=API_KEY
    ):Response<NewsResponse>

}