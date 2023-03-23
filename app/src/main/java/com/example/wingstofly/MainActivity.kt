package com.example.wingstofly

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.wingstofly.database.*
import com.example.wingstofly.databinding.ActivityMainBinding
import com.example.wingstofly.models.*
import com.example.wingstofly.repository.QuizRepository
import com.example.wingstofly.utils.Constants
import com.example.wingstofly.viewmodel.QuizProviderFactory
import com.example.wingstofly.viewmodel.QuizViewModel
import com.google.firebase.database.*
import com.skydoves.transformationlayout.onTransformationStartContainer

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private lateinit var mAuth: DatabaseReference
    lateinit var questViewModel: QuizViewModel
    var scholars =  ArrayList<Scholar>()
    lateinit var jobs: ArrayList<Job>
    lateinit var events: ArrayList<Event>
    lateinit var schools: ArrayList<Upskill>
    private var pfNumber: String? = null
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer() // called before the super.onCreate method
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

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
        val jobManager = JobsDataManager()
        val eventManager = EventsDataManager()
        events = eventManager.allEvents

        mAuth.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){
                    scholars.add(data.getValue(Scholar::class.java)!!)
                }
                bind.progress.isVisible = false
            }

            override fun onCancelled(error: DatabaseError) {
                bind.progress.isVisible = false
                bind.connection.isVisible = true
                bind.fragmentContainerView.isVisible = false
                bind.connection.text = "Check your internet connection"
                Toast.makeText(this@MainActivity,  error.message, Toast.LENGTH_LONG).show()
            }

        })
        jobs = jobManager.allJobs
        schools = UpSkillDataManager().schools

    }

}