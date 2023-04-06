package com.example.wingstofly.repository

import com.example.wingstofly.api.RetrofitClient


class QuizRepository {

    suspend fun getTopics() = RetrofitClient.getApiClient.getCategories()

    suspend fun getQuestions(amount: String = "15", difficulty: String = "hard", type: String = "multiple", category: String = "9") =
        RetrofitClient.getApiClient.getQuetsions(amount, category, difficulty, type)


}