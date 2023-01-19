package com.example.wingstofly.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wingstofly.models.Topic
import com.example.wingstofly.repository.QuizRepository
import com.example.wingstofly.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class QuizViewModel(private val quizRepository: QuizRepository): ViewModel() {
    val questions: MutableLiveData<Resource<Topic>> = MutableLiveData() //posts api request state to the observer
    val amount: String = "10" ;val difficulty: String = "hard"; val type: String = "multiple"; val category: String = "any"


    private fun getQuestions() = viewModelScope.launch {
        questions.postValue(Resource.Loading())
        val response = quizRepository.getQuestions() // getting the response from the repository
        questions.postValue(getResponseState(response)) // posting the current state of the request to the observer
    }

    private fun getResponseState(response: Response<Topic>): Resource<Topic>{ //getting the requests current state
        if(response.isSuccessful){
            response.body()?.let { topic ->
                return Resource.Success(topic)
            }
        }
        return Resource.Failure(message = response.message())

    }
}