package com.example.wingstofly.api

import com.example.wingstofly.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient() {
    companion object{

        private val retrofit by lazy {
            Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val getApiClient by lazy {
            retrofit.create(Topics::class.java)
        }
    }
}