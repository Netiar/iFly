package com.example.wingstofly.api

import com.example.wingstofly.models.Topic
import com.example.wingstofly.models.TopicCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Topics {
    @GET("api_category.php")
    suspend fun getCategories():Response<TopicCategory>

    @GET("api.php")
    suspend fun getQuetsions(
        @Query("amount") amount: String,
        @Query("category")category:String,
        @Query("difficulty") difficulty:String,
        @Query("type") type: String
    ): Response<Topic>
}