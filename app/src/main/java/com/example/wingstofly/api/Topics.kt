package com.example.wingstofly.api

import com.example.wingstofly.models.TopicCategory
import retrofit2.Response
import retrofit2.http.GET

interface Topics {
    @GET("api_category.php")
    suspend fun getCategories():Response<TopicCategory>
}