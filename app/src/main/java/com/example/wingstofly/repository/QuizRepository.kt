package com.example.wingstofly.repository

import com.example.wingstofly.api.RetrofitClient
import com.example.wingstofly.database.DataManager
import com.example.wingstofly.models.Topic
import retrofit2.Response

class QuizRepository {
    private val dataManager = DataManager()

    suspend fun getTopics() = RetrofitClient.getApiClient.getCategories()

    suspend fun getQuestions(amount: String = "10", difficulty: String = "hard", type: String = "multiple", category: String = "any") =
        RetrofitClient.getApiClient.getQuetsions(amount, category, difficulty, type)


}