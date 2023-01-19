package com.example.wingstofly.repository

import com.example.wingstofly.api.RetrofitClient
import com.example.wingstofly.models.Topic
import retrofit2.Response

class QuizRepository {

    suspend fun getQuestions(amount: String = "10", difficulty: String = "hard", type: String = "multiple", category: String = "any") =
        RetrofitClient.getApiClient.getQuetsions(amount, category, difficulty, type)

}