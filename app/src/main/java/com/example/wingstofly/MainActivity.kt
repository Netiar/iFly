package com.example.wingstofly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.wingstofly.database.DataScholarManager
import com.example.wingstofly.database.EventsDataManager
import com.example.wingstofly.database.JobsDataManager
import com.example.wingstofly.database.Subjects
import com.example.wingstofly.models.Event
import com.example.wingstofly.models.Job
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.models.Subject
import com.example.wingstofly.repository.QuizRepository
import com.example.wingstofly.viewmodel.QuizProviderFactory
import com.example.wingstofly.viewmodel.QuizViewModel
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: DatabaseReference
    lateinit var questViewModel: QuizViewModel
    lateinit var scholars: ArrayList<Scholar>
    lateinit var subjects: ArrayList<Subject>
    lateinit var jobs: ArrayList<Job>
    lateinit var events: ArrayList<Event>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseDatabase.getInstance().reference

        val repo = QuizRepository()
        val provider = QuizProviderFactory(repo)
        questViewModel = ViewModelProvider(this, provider)[QuizViewModel::class.java]
        val dm = DataScholarManager()
        val subject = Subjects()
        val jobManager = JobsDataManager()
        val eventManager = EventsDataManager()
        events = eventManager.allEvents
        scholars = dm.scholars
        subjects = subject.allSubjects
        jobs = jobManager.allJobs
        for (scholar in scholars){
            for(subject in subjects){
                when(subject.category){
                    "Common" -> subject.score = 73
                    "Humanities" -> subject.score = 86
                    "Sciences" -> subject.score = 56
                    else -> subject.score = 63
                }

                scholar.addSubject(subject)
            }
        }
        addUsers(scholars)


    }

    private fun addUsers(scholars: ArrayList<Scholar>) {
        for (scholar in scholars){
            mAuth.child("user").child(scholar.name!!).setValue(scholar)
        }
    }
}