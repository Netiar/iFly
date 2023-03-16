package com.example.wingstofly

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModelProvider
import com.example.wingstofly.database.*
import com.example.wingstofly.models.*
import com.example.wingstofly.repository.QuizRepository
import com.example.wingstofly.utils.Constants
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
    lateinit var scholarMessages: ArrayList<Message>
    var scholarSuggestion =  ArrayList<Scholar>()
    lateinit var schools: ArrayList<Upskill>
    var realScholar: Scholar? = null

    private var pfNumber: String? = null
    private lateinit var pref: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialising late init vars
        mAuth = FirebaseDatabase.getInstance().reference
        pref = PreferenceManager.getDefaultSharedPreferences(this)

        //getting the scholars pf number
        pfNumber = pref.getString(Constants.PF_NUMBER, null)

        //initialising the quiz repository and provider
        val repo = QuizRepository()
        val provider = QuizProviderFactory("", repo)
        questViewModel = ViewModelProvider(this, provider)[QuizViewModel::class.java]

        //getting data from the data managers
        val dm = DataScholarManager()
        val subject = Subjects()
        val jobManager = JobsDataManager()
        val eventManager = EventsDataManager()
        scholarMessages = ArrayList()
        events = eventManager.allEvents
        scholars = dm.scholars
        subjects = subject.allSubjects
        jobs = jobManager.allJobs
        schools = UpSkillDataManager().schools

        //getting the scholar using the app
        realScholar = getScholar(pfNumber)

        // assigning the scholar subjects marks
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


    }

    //defining a method to get the actual scholar
    private fun getScholar(pfNumber: String?): Scholar{
        var realScholar: Scholar? = null
        for (i in scholars.indices){
            if (pfNumber.contentEquals(scholars[i].pfNumber)){
                realScholar = scholars[i]
                for (i in scholars.indices){
                    if ((scholars[i].origin == realScholar.origin) || (scholars[i].secondarySchool == realScholar.secondarySchool)){
                        scholarSuggestion.add(scholars[i])
                    }
                }
            }

        }
        scholarSuggestion.remove(realScholar)
        return realScholar!!
    }
}