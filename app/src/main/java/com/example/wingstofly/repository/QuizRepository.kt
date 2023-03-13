package com.example.wingstofly.repository

import com.example.wingstofly.api.RetrofitClient


class QuizRepository {
//    private val dataManager = DataMa?nager()

    suspend fun getTopics() = RetrofitClient.getApiClient.getCategories()

    suspend fun getQuestions(amount: String = "10", difficulty: String = "hard", type: String = "multiple", category: String = "any") =
        RetrofitClient.getApiClient.getQuetsions(amount, category, difficulty, type)


}