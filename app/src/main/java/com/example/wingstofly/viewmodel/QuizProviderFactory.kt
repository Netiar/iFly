package com.example.wingstofly.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wingstofly.repository.QuizRepository

class QuizProviderFactory(private val cat: String, private val quizRepository: QuizRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuizViewModel(cat, quizRepository) as T
    }
}