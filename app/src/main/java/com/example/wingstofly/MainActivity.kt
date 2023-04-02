package com.example.wingstofly

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.wingstofly.databinding.ActivityMainBinding
import com.example.wingstofly.models.*
import com.example.wingstofly.repository.QuizRepository
import com.example.wingstofly.viewmodel.QuizProviderFactory
import com.example.wingstofly.viewmodel.QuizViewModel
import com.google.firebase.database.*
import com.skydoves.transformationlayout.onTransformationStartContainer

class MainActivity : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    private lateinit var bind: ActivityMainBinding
    private lateinit var mAuth: DatabaseReference
    lateinit var questViewModel: QuizViewModel
    var scholars =  ArrayList<Scholar>()
    var jobs =  ArrayList<Job>()
    var events = ArrayList<Event>()
    var schools =  ArrayList<Upskill>()


    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer() // initiating view transformation
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        //initialising late init vars
        mAuth = FirebaseDatabase.getInstance().reference
        pref = PreferenceManager.getDefaultSharedPreferences(this)

        //initialising the quiz repository and provider
        val repo = QuizRepository()
        val provider = QuizProviderFactory("", repo)
        questViewModel = ViewModelProvider(this, provider)[QuizViewModel::class.java]



        //getting the scholars from the database
        mAuth.child("scholars").addValueEventListener(object : ValueEventListener {
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

        //getting jobs from the database
        mAuth.child("jobs").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(job in snapshot.children){
                    jobs.add(job.getValue(Job::class.java)!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_LONG).show()
            }

        })

        //getting events from the database
        mAuth.child("events").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(event in snapshot.children){
                    events.add(event.getValue(Event::class.java)!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_LONG).show()
            }

        })

        //getting upskill schools from the database
        mAuth.child("schools").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(school in snapshot.children){
                    schools.add(school.getValue(Upskill::class.java)!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_LONG).show()
            }

        })

    }

}