package com.example.weatherapp.api

import com.example.weatherapp.BuildConfig.API_KEY
import com.example.weatherapp.BuildConfig.BaseUrl
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
* Here we are creating a singleton Retrofit instance class so that we can call our API from any where in App.
* */

class RetrofitInstance {

    //companion objects are like static objects in java
    companion object {

        //Lazy means that we are initialing it only once
        private val retrofit by lazy {

            //Here we are attaching the interceptor to the retrofit to view the body of response
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            //We are using the interceptor to the network client
            val client = OkHttpClient.Builder()
                            .addInterceptor(logging)
                            .build()

            Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        }

        //Getting API instance for retrofit builder and pass Interface that we created
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }

}