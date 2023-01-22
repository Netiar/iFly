package com.example.wingstofly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.wingstofly.database.DataScholarManager
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.repository.QuizRepository
import com.example.wingstofly.viewmodel.QuizProviderFactory
import com.example.wingstofly.viewmodel.QuizViewModel

class MainActivity : AppCompatActivity() {
    lateinit var questViewModel: QuizViewModel
    lateinit var scholars: ArrayList<Scholar>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = QuizRepository()
        val provider = QuizProviderFactory(repo)
        questViewModel = ViewModelProvider(this, provider)[QuizViewModel::class.java]
        val dm = DataScholarManager()
        scholars = dm.scholars

    }
}