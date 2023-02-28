package com.example.wingstofly

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.wingstofly.adapters.ChatRecHolder
import com.example.wingstofly.database.DataScholarManager
import com.example.wingstofly.database.EventsDataManager
import com.example.wingstofly.database.JobsDataManager
import com.example.wingstofly.database.Subjects
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
    lateinit var scholarSuggestion: ArrayList<Scholar>
    lateinit var hashMap: HashMap<Scholar, Message>
    lateinit var chatAdapter: ChatRecHolder
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
        val provider = QuizProviderFactory(repo)
        questViewModel = ViewModelProvider(this, provider)[QuizViewModel::class.java]

        //getting data from the data managers
        val dm = DataScholarManager()
        val subject = Subjects()
        val jobManager = JobsDataManager()
        val eventManager = EventsDataManager()
        scholarMessages = ArrayList()
        scholarSuggestion = ArrayList()
        hashMap = getScholarMessages()
        chatAdapter = ChatRecHolder(hashMap, this)
        events = eventManager.allEvents
        scholars = dm.scholars
        subjects = subject.allSubjects
        jobs = jobManager.allJobs

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
        addUsers(scholars)


    }

    private fun addUsers(scholars: ArrayList<Scholar>) {
        for (scholar in scholars){
            mAuth.child("user").child(scholar.name!!).setValue(scholar)
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

    private fun getScholarMessages(): HashMap<Scholar, Message> {
        var methodHashmap = HashMap<Scholar, Message>()
        mAuth.child("chats").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children){
                    if(dataSnapshot.key!!.substring(0,7) == pfNumber ){
//                        Toast.makeText(requireContext(), dataSnapshot.key!!.substring(0,7), Toast.LENGTH_LONG).show()

                        mAuth.child("chats").child(dataSnapshot.key!!).child("messages").addValueEventListener(object: ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val scholarPf = dataSnapshot.key!!.substring(7)
                                for (receiver in scholars){
                                    if(receiver.pfNumber == scholarPf){
                                        for (messageSnapshot in snapshot.children){
                                            scholarMessages.add(messageSnapshot.getValue(Message::class.java)!!)
                                        }
                                        methodHashmap!![receiver] = scholarMessages[scholarMessages.size - 1]
                                        Toast.makeText(this@MainActivity,
                                            methodHashmap.keys.elementAt(0).status, Toast.LENGTH_LONG).show()

                                    }
                                }
                                chatAdapter.notifyDataSetChanged()
                            }

                            override fun onCancelled(error: DatabaseError) {

                            }

                        })
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        } )

        return methodHashmap
    }

}